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
 * @author Junming, Yang
 *
 */

@Slf4j // 로그
@Controller
public class MemberCotroller {
	
	@Autowired
	MemberDTO memberDTO;
	
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
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		mv.addObject("memberDTO", memberDTO);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-edit");
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-edit");
		}
		
		return mv;
	}
	
	// 회원 정보 수정 결과 
	@PostMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth, MultipartFile thumbnail, String nickname, String interest) throws IOException {
		
		ModelAndView mv = new ModelAndView();
		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		String mbId = parseMbId.parseMbId(auth);

		// 컷 부분 
		MemberDTO memberDTO = memberService.updateuser(mbId, thumbnail, nickname, interest);

		mv.addObject("memberDTO", memberDTO);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("redirect:/mypage");
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("redirect:/mypage");
		}
		
		return mv;
	}
	
	// 닉네임 중복확인(비동기)
	@ResponseBody
	@RequestMapping("/edit/nickname")
	public Map<String, String> nickname(String nickcheck, String mbId, Authentication auth) { // Authentication auth -> mbId로 연결하기 & 수정 & 권한 업데이트
		System.out.println(nickcheck);
		Map<String, String> map = new HashMap<String, String>();
		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		if (nickcheck.equals("") ){
			map.put("result", "blank");		
			System.out.println("blank");
		}
		else if (securityDAO.findByMbNick(nickcheck) == null || securityDAO.findByMbNick(nickcheck).getMbId().equals(mbId) ) { // 없거나, 본인이면
			System.out.println("ok");
			map.put("result", "ok");
			map.put("nickcheck", nickcheck);
		}
		else{
			map.put("result", "duplicated");	
			System.out.println("duplicated");
		}
		return map;		
	}
	
	
	// 회원 탈퇴 페이지 진입 
	@GetMapping("/mypage/withdraw")
	public ModelAndView	deleteuser(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();
		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		mv.addObject("memberDTO", memberDTO);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-withdraw"); 
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-withdraw");
		}
		
		return mv;
	}
	
	// 회원 탈퇴 결과
	@PostMapping("/mypage/withdraw")
	public String deleteuserresult(HttpServletRequest req, Authentication auth) {
		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		if (memberDTO.getMbRole() == Role.GUARD) { // 보호자일 때 연결된 아이도 모두 함꼐 탈퇴
			try {
				List<MemberDTO> childlist = securityDAO.findByMbGuard(mbId);
				System.out.println("childlist: "+childlist);
				for (MemberDTO childMemberDTO : childlist) { // childlist.size()
					System.out.println("childMemberDTO: "+childMemberDTO);
					String childMbId = childMemberDTO.getMbId();
					memberDAO.updatedelete(childMbId); // 한 명씩 모두 탈퇴
				};			
			} catch (Exception err) {
				err.printStackTrace();
			}
		}

		// 아이 모두 탈퇴시킨 뒤 보호자 탈퇴
		memberDAO.updatedelete(mbId); 
		
		// 세션에 변경사항 저장
		SecurityContext context = SecurityContextHolder.getContext();
		// UsernamePasswordAuthenticationToken
		context.setAuthentication(new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, null)); // Role 삭제
		HttpSession session = req.getSession(true);
		//위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
		session.setAttribute(HttpSessionSecurityContextRepository.
		                       SPRING_SECURITY_CONTEXT_KEY, context);
		
		// 탈퇴하면 로그인 페이지로 보내기			
		return "member/login";
	}
	
	// 프로필사진은 1개만 지정. 원래 파일명 저장 X. 삭제시 DB에서 삭제. 회원 탈퇴시 사진도 자동 삭제(사진이 컬럼이므로 따로 처리 필요 X)
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
		if(!memberDTO.getMbThumbnail().isEmpty()) {

			File file = new File(memberDTO.getMbThumbnail());
			
			if (file.exists()) {
				file.delete(); // 삭제
			}

			// db에 업데이트 하기(저장경로 + 파일 이름)
			memberDAO.updatedelthumbnail(mbId);
			
			map.put("result", "success");
			
			return map;
		}
		else {
			map.put("result", "failure");
			return map;
		}
		
	}
	
}
