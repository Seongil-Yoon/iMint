package multi.fclass.iMint.member.controller;

import java.io.File;
import java.io.IOException;
import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;
import multi.fclass.iMint.common.code.ErrorCode;
import multi.fclass.iMint.common.exception.hadler.UnauthorizedException;
import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.service.IMemberService;
import multi.fclass.iMint.security.controller.IndexController;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Jungmin, Yang
 *
 */

@Slf4j // 로그
@Controller
public class MemberCotroller {

	@Autowired
	IMemberDAO memberDAO;

	@Autowired
	ISecurityDAO securityDAO;

	@Autowired
	ParseMbId parseMbId;

	@Autowired
	IMemberService memberService;

	@Autowired
	IFileService fileService;

	@Value("${root}")
	private String root;

	@Value("${directory}")
	private String directory;

	@Value("${memberImagePath}")
	String memberImagePath;

	// 회원 정보 수정 페이지 진입
	@GetMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth) {

		ModelAndView mv = new ModelAndView();
		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		mv.setViewName("mypage/edit");
		return mv;
	}

	// 회원 정보 수정 결과
	@PostMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth, MultipartFile thumbnail, String nickname, String interest)
			throws IOException {

		ModelAndView mv = new ModelAndView();
		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		// 관심사 선택 안 했을 때 (보호자)
		if (interest == null) {
			interest = "";
		}

		String mbId = parseMbId.parseMbId(auth);

		// 컷 부분
		memberService.updateuser(mbId, thumbnail, nickname, interest);

		mv.setViewName("redirect:/mypage");
		return mv;
	}

	// 닉네임 중복확인(비동기)
	@ResponseBody
	@RequestMapping("/edit/nickname")
	public Map<String, String> nickname(String nickcheck, String mbId, Authentication auth) { // Authentication auth ->
																								// mbId로 연결하기 & 수정 & 권한
		Map<String, String> map = new HashMap<String, String>();
		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		if (nickcheck.equals("")) {
			map.put("result", "blank");
			System.out.println("blank");
		} else if (securityDAO.findByMbNick(nickcheck) == null
				|| securityDAO.findByMbNick(nickcheck).getMbId().equals(mbId)) { // 없거나, 본인이면
			System.out.println("ok");
			map.put("result", "ok");
			map.put("nickcheck", nickcheck);
		} else {
			map.put("result", "duplicated");
			System.out.println("duplicated");
		}
		return map;
	}

	// 회원 탈퇴 페이지 진입
	@GetMapping("/mypage/withdraw")
	public ModelAndView deleteuser(Authentication auth) {

		ModelAndView mv = new ModelAndView();
		
		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		mv.setViewName("mypage/withdraw");
		return mv;
	}

	// 회원 탈퇴 결과
	@PostMapping("/mypage/withdraw")
	public ModelAndView deleteuserresult(HttpServletRequest req, Authentication auth) {
		
		ModelAndView mv = new ModelAndView();
		
		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		// 보호자 탈퇴인 경우: 아이 모두 탈퇴시킨 뒤 보호자 탈퇴
		if (memberDTO.getMbRole() == Role.GUARD) { // 보호자일 때 연결된 아이도 모두 함께 탈퇴시킨다 
			memberDTO.setMbRole(Role.UN_GUARD); // 미인증 회원으로 강등 
			try {
				List<MemberDTO> childlist = securityDAO.findByMbGuard(mbId);
				System.out.println("childlist: " + childlist);
				for (MemberDTO childMemberDTO : childlist) { 
					System.out.println("childMemberDTO: " + childMemberDTO);
					String childMbId = childMemberDTO.getMbId();
					// 미인증 회원으로 강등 
					memberDAO.updatedelete(childMbId, Role.UN_CHILD); // 한 명씩 모두 탈퇴
				};
			} catch (Exception err) {
				err.printStackTrace();
			}
			
			// 연결된 아이를모두 탈퇴시킨 후 보호자 본인도 탈퇴
			memberDAO.updatedelete(mbId, Role.UN_GUARD);
		}

		// 아이 탈퇴인 경우: 혼자 탈퇴 
		if (memberDTO.getMbRole() == Role.CHILD) {
			memberDAO.updatedelete(mbId, Role.UN_CHILD);
		}

		// 세션 수정
	    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
	    authorities.add(new SimpleGrantedAuthority(memberDTO.getRoleKey()));
		
		// 세션에 변경사항 저장
		SecurityContext context = SecurityContextHolder.getContext();
		// UsernamePasswordAuthenticationToken
		context.setAuthentication(new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, authorities)); // Role을 미인증 회원(UN_CHILD, UN_GUARD)으로 업데이트
		HttpSession session = req.getSession(true);
		// 위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
		session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);

		// 탈퇴하면 로그인 페이지로 보내기
		mv.addObject("withdraw", "true");
		mv.setViewName("index");
		return mv;
	}

	// 프로필사진은 1개만 지정. 원래 파일명 저장 X. 삭제시 DB에서 삭제. 회원 탈퇴시 사진도 자동 삭제(사진이 컬럼이므로 따로 처리 필요
	// X)
	@ResponseBody
	@RequestMapping("/mypage/edit/delete/thumbnail")
	public Map<String, String> delete(Authentication auth) throws IOException {

		Map<String, String> map = new HashMap<String, String>();

		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		// 파일 삭제
		if (!memberDTO.getMbThumbnail().isEmpty()) {

			File file = new File(memberDTO.getMbThumbnail());

			if (file.exists()) {
				file.delete(); // 삭제
			}

			// db에 업데이트 하기(저장경로 + 파일 이름)
			memberDAO.updatedelthumbnail(mbId);

			map.put("result", "success");

			return map;
		} else {
			map.put("result", "failure");
			return map;
		}

	}

}
