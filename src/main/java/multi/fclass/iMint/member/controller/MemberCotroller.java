package multi.fclass.iMint.member.controller;

import java.io.File;
import java.io.IOException;
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

import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

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
	
	@Value("${directory}")
	private String directory;
	
	@RequestMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth, String thumbnail, String nickname, String interest) {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		
//		User user = parseMbId.getUserMbId(mbId);
//		user.setMbThumbnail(thumbnail);
//		user.setMbNick(nickname);
//		user.setMbInterest(interest);
		
		memberDAO.updatemember(mbId, thumbnail, nickname, interest);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-edit");
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-edit");
		}
		
		return mv;
	}
	
//	@GetMapping("/mypage/withdraw")
	public ModelAndView	deleteuser(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-withdraw"); 
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-withdraw");
		}
		
		return mv;
	}
	
//	@PostMapping("/mypage/withdraw")
	public String deleteuserresult(HttpServletRequest req, Authentication auth) {
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		memberDAO.updatedelete(mbId); 
		
	//  cascade로 할 수 있는지? 우선은 리스트에 아이를 담아서 처리(테스트필요). 보호자가 탈퇴하면 자동으로 아이도 탈퇴처리 필요
		if (memberDTO.getMbRole() == Role.GUARD) { // 보호자일 때만 처리
			
			List<MemberDTO> childlist = securityDAO.findByMbGuard(mbId);
			for (Iterator iterator = childlist.iterator(); iterator.hasNext();) { // childlist.size()
				MemberDTO childmember = (MemberDTO) iterator.next();
				memberDAO.updatedelete(childmember.getMbId()); // 한 명씩 모두 탈퇴
			}
			
		}
		
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
	@RequestMapping("/mypage/edit/thumbnail")
	public Map<String, String> upload(MultipartFile thumbnail, Authentication auth) throws IOException { // @ModelAttribute("뷰가 받을 이름"): 뷰로 전달해주고 싶을 때.

		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println(thumbnail.getOriginalFilename()); //dto.getFile1():Multipartfile 이 toString 메서드 오버라이딩하지 않았으면 패키지명.클래스명@16진수 주소 로 출력.
		System.out.println(thumbnail.getSize());
		System.out.println(thumbnail.isEmpty()); // isEmpty: 파일 전송 여부를 boolean으로. 
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		String mbRole = memberDTO.getMbRole().toString();
		
		// ex. ../naver/GUARD/naver_sdfklw242.jpg
		String savePath = directory + "/" + memberDTO.getMbProvider() + "/" + mbRole.substring(mbRole.length()-5, mbRole.length()); // 저장경로: 1.provider 별로 지정 2. guard / child 별로 지정
		
		if(!thumbnail.isEmpty()) {
			
			// 원래 파일 명에서 확장자(.) 추출 
			String ext = thumbnail.getOriginalFilename().substring(thumbnail.getOriginalFilename().indexOf("."));

			// 파일내용 + 파일명 --> 서버의 특정폴더(c:upload)에 영구저장. 서버가 종료되더라도 폴더에 저장.
			String newname = savePath + "_" + mbId + ext;
			String allname = savePath + newname;
			
			File serverfile = new File(newname);
			thumbnail.transferTo(serverfile);
			
			// db에 업데이트 하기(저장경로 + 파일 이름)
			memberDAO.updatethumbnail(mbId, allname);
			
			map.put("success", allname);
			
			return map;
		}
		else {
			map.put("fail", "isEmpty");
			return map;
		}
		
	}
	
}
