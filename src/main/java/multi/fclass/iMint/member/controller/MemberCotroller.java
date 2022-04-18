package multi.fclass.iMint.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.security.dao.IUserDAO;
import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

@Controller
public class MemberCotroller {
	
	@Autowired
	User user;
	
	@Autowired
	IMemberDAO memberDAO;
	
	@Autowired
	IUserDAO userDAO;
	
	@Autowired
	ParseMbId parseMbId;
	
	@RequestMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth, String thumbnail, String nickname, String interest) {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		
//		User user = parseMbId.getUserMbId(mbId);
//		user.setMbThumbnail(thumbnail);
//		user.setMbNick(nickname);
//		user.setMbInterest(interest);
		
		memberDAO.updateuser(mbId, thumbnail, nickname, interest);
		
		if(user.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-edit");
		}
		else if(user.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-edit");
		}
		
		return mv;
	}
	
	@GetMapping("/mypage/withdraw")
	public ModelAndView	deleteuser(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		User user = parseMbId.getUserMbId(mbId);
		
		if(user.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-withdraw"); 
		}
		else if(user.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-withdraw");
		}
		
		return mv;
	}
	
	@PostMapping("/mypage/withdraw")
	public String deleteuserresult(HttpServletRequest req, Authentication auth) {
		
		String mbId = parseMbId.parseMbId(auth);
		memberDAO.updatedelete(mbId); //  cascade로 보호자가 탈퇴하면 자동으로 아이도 탈퇴
		
		// 세션에 변경사항 저장
		SecurityContext context = SecurityContextHolder.getContext();
		// UsernamePasswordAuthenticationToken
		context.setAuthentication(new UsernamePasswordAuthenticationToken(user.getMbId(), null, null));
		HttpSession session = req.getSession(true);
		//위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
		session.setAttribute(HttpSessionSecurityContextRepository.
		                       SPRING_SECURITY_CONTEXT_KEY, context);
		
		// 탈퇴하면 로그인 페이지로 보내기			
		return "member/login";
	}
	
}
