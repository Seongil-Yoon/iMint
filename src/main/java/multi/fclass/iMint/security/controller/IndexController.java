package multi.fclass.iMint.security.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import ch.qos.logback.core.net.SyslogOutputStream;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.dto.SessionMember;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.GenerateCertCharacter;
import multi.fclass.iMint.security.auth.provider.KakaoUserInfo;
import multi.fclass.iMint.security.auth.provider.NaverUserInfo;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.security.parsing.role.ParseMbRole;

/**
 * @author Junming, Yang
 *
 */

@Slf4j // 로그
@Controller // 뷰 반환
public class IndexController {

	@Autowired
	private ISecurityDAO securityDAO;
	
	@Autowired
	private MemberDTO memberDTO;
	
	@Autowired
	ParseMbRole parseMbRole;
	
	@Autowired
	ParseMbId parseMbId;
	
    @Autowired
    private AuthenticationManager authenticationManager; // 세션값 변경목적
    
    private HttpSession httpSession;    
	
    // 로그인 
	@GetMapping({"", "/"})
	public String loginForm() {
		return "member/login";
	}
	
	// 가입 완료시키기
	@RequestMapping(value = "/err/denied-page") // 안온다 
	public ModelAndView accessDenied(Authentication auth, HttpServletRequest req){
		
		ModelAndView mv = new ModelAndView();
		
		AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
//        log.info("---------- Log 테스트 ---------");
//		log.info("auth : {}", auth.getPrincipal()); // 로그 기록
//		log.info("exception : {}", ade); // 로그 기록
		
		// 모듈화 결과(아래 1줄)
//		String mbId = parseMbId.parseMbId(auth);
//		User user = parseMbId.getMemberMbId(mbId);
//		String mbRole = user.getMbRole().toString();
//		System.out.println(mbRole);
//		mv.addObject("mbRole", mbRole);
//		mv.addObject("errMsg", ade);
		
		mv.setViewName("err/deniedpage");
		
		return mv;
	}

	// 회원가입은 총 4단계 
	// 회원가입 2(보호자, 아이 모두): sns 가입(회원가입 1)후 register 페이지로 이동
	@GetMapping("/register")
	public ModelAndView registersns(Authentication auth) { // Authentication auth -> mbId로 연결하기 & 수정 & 권한 업데이트

		// 모듈화 결과(아래 2줄)
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO user = parseMbId.getMemberMbId(mbId);
		
		ModelAndView mv = new ModelAndView();

		mv.addObject("user", user);
		mv.setViewName("member/register");
		return mv;
	}
	
	@ResponseBody
	@RequestMapping("/register/nickname")
	public Map<String, String> nickname(String nickcheck, String mbId, Authentication auth) { // Authentication auth -> mbId로 연결하기 & 수정 & 권한 업데이트

		Map<String, String> map = new HashMap<String, String>();
		
		System.out.println(nickcheck);
		
		if (securityDAO.findByMbNick(nickcheck) == null || securityDAO.findByMbNick(nickcheck).getMbId().equals(mbId) ) { // 없거나, 본인이면
			map.put("result", "ok");
			map.put("nickcheck", nickcheck);
		}
		else {
			map.put("result", "duplicated");			
		}
		return map;		
	}
	
	// 회원가입 3(보호자, 아이 모두. 로직은 분리)
	@PostMapping("/register") //
	public ModelAndView registersns(HttpServletRequest req, String mbId, String mbRole, String mbNick, String mbEmail, String mbInterest) { // Authentication auth -> mbId로 연결하기 & 수정 & 권한 업데이트

		ModelAndView mv = new ModelAndView();
		
		// 유저 정보 업데이트 
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		memberDTO.setMbNick(mbNick);
		memberDTO.setMbEmail(mbEmail);
		memberDTO.setMbInterest(mbInterest);
		
		securityDAO.updateregister3(memberDTO);
		
		if(mbRole.equals("UN_GUARD")) {
			mv.setViewName("member/guard-mypage/guard-location");
		}
		else if(mbRole.equals("UN_CHILD")) {
			mv.setViewName("member/register_connect");			
		}

		mv.addObject("memberDTO", memberDTO);
		return mv;
	}
	

	// 회원가입 3(보호자): 내 동네 설정 -> 보호자, 관리자 권한 부여

//	@ResponseBody
////	@RequestMapping(value = "/mypage/location", method = RequestMethod.GET, produces = {"application/json;charset=utf-8"})
//	@RequestMapping("/mypage/location")
//	public ModelAndView GUARDlocation(String mbId, String mbNick, Role mbRole, String mbEmail, String mbInterest, String mbLocation) { // String mbId, String mbNick, Role mbRole, String mbEmail, String mbInterest
//		
//		ModelAndView mv = new ModelAndView();
//		
//		User user = parseMbId.getMemberMbId(mbId);
//		System.out.println(mbId);
//		System.out.println(mbNick);
//		System.out.println(mbRole);
//
//		//		user.setMbNick(mbNick);
////		user.setMbRole(mbRole);
////		user.setMbEmail(mbEmail);
////		user.setMbInterest(mbInterest);
////		user.setMbLocation(mbLocation);
////		userdao.savedetails(mbId, mbNick, mbRole, mbEmail, mbInterest, mbLocation, null); // 1차 저장 
//		mv.addObject("user", user);  // 객체 추가할 때 user 객체 
//		mv.setViewName("member/guard-mypage/guard-location");
//		return mv;
//	}
//	
//	// 회원가입 3(아이): 보호자 연결 설정 -> 아이, 관리자 권한 부여
//	//@PreAuthorize("hasRole('ROLE_uncerti_CHILD') or hasRole('ROLE_CHILD') or hasRole('ROLE_ADMIN')")
//	@GetMapping("/register/connect")		
//	public ModelAndView babyconnect(String mbId, String mbNick, Role mbRole, String mbEmail, String mbInterest) {
//		ModelAndView mv = new ModelAndView();
//		User user = userdao.findByMbId(mbId);
//		user.setMbId(mbId);
//		user.setMbNick(mbNick);
//		user.setMbRole(mbRole);
//		user.setMbEmail(mbEmail);
//		user.setMbInterest(mbInterest);
//		
//		mv.addObject("user", user);  // 객체 추가할 때 user 객체 
//		mv.setViewName("member/register_connect");
//		return mv;
//	}

	
	// 회원가입 4(최종. 보호자, 아이 모두)
	// 회원가입 마치면 부모-> 위치 설정 , 아이 -> 보호자 연동 후 권한을 인증으로 변경
	// 회원가입 후 다시 로그인 요청
	@RequestMapping("/register/complete")
	public ModelAndView registerdetails(HttpServletRequest req, Authentication auth, String mbLocationOrGuard, String guardPin) {
	
	ModelAndView mv = new ModelAndView();
	
	String mbId = parseMbId.parseMbId(auth);
	MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
	mv.addObject("memberDTO", memberDTO);

	try {
		// mbLocation 받아오기 
		if (memberDTO.getMbRole() == Role.UN_GUARD) { // 보호자 
			memberDTO.setMbGuard(null);
			memberDTO.setMbLocation(mbLocationOrGuard);
			memberDTO.setMbRole(Role.GUARD);
			memberDTO.setMbPin(new GenerateCertCharacter().excuteGenerate());
			mv.setViewName("member/login");
			
			// DB저장
			securityDAO.updateregister4(memberDTO);
		
			// 세션 수정
		    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
		    authorities.add(new SimpleGrantedAuthority(memberDTO.getRoleKey()));
			// 세션에 변경사항 저장
			SecurityContext context = SecurityContextHolder.getContext();
			// UsernamePasswordAuthenticationToken
			context.setAuthentication(new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, authorities));
			HttpSession session = req.getSession(true);
			//위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
			session.setAttribute(HttpSessionSecurityContextRepository.
			                       SPRING_SECURITY_CONTEXT_KEY, context);
			
		}	
		else if (memberDTO.getMbRole() == Role.UN_CHILD) { // 아이 
			MemberDTO guardMember = securityDAO.findByMbNick(mbLocationOrGuard);
			try {
				if (guardMember != null & guardMember.getMbPin().equals(guardPin)) {
					memberDTO.setMbGuard(guardMember.getMbId());
					memberDTO.setMbLocation(null);
					memberDTO.setMbRole(Role.CHILD);
					memberDTO.setMbPin(null);
					mv.setViewName("member/login");
					
					// DB저장
					securityDAO.updateregister4(memberDTO);
				
					// 세션 수정
				    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
				    authorities.add(new SimpleGrantedAuthority(memberDTO.getRoleKey()));
					// 세션에 변경사항 저장
					SecurityContext context = SecurityContextHolder.getContext();
					// UsernamePasswordAuthenticationToken
					context.setAuthentication(new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, authorities));
					HttpSession session = req.getSession(true);
					//위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
					session.setAttribute(HttpSessionSecurityContextRepository.
					                       SPRING_SECURITY_CONTEXT_KEY, context);
				}
				else { // 보호자의 입력정보가 틀리면 다시 보내기
					mv.setViewName("redirect:/register");
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
				mv.setViewName("redirect:/register");	// 보호자의 입력정보가 틀리면 다시 보내기
				mv.addObject("err", "정확한 보호자의 정보를 입력하세요.");
			}			
				return mv;
		}	
		else if(memberDTO.getMbRole() == Role.GUARD){ // 가입 완료된 보호자
			mv.setViewName("redirect:/mypage/location");
			return mv;
		}	

//			// DB저장
//			securityDAO.updateregister4(memberDTO);
//		
//			// 세션 수정
//		    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();   
//		    authorities.add(new SimpleGrantedAuthority(memberDTO.getRoleKey()));
//			// 세션에 변경사항 저장
//			SecurityContext context = SecurityContextHolder.getContext();
//			// UsernamePasswordAuthenticationToken
//			context.setAuthentication(new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, authorities));
//			HttpSession session = req.getSession(true);
//			//위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
//			session.setAttribute(HttpSessionSecurityContextRepository.
//			                       SPRING_SECURITY_CONTEXT_KEY, context);
		
			return mv;

	} // try end 
		
	catch (ClassCastException e) {
		mv.setViewName("redirect:/mypage/location");
		return mv;
	} // catch end
		
	}
		
	// SecuritConfig에서 secured어노테이션 활성화: securedEnabled = true
	// @Secured: 권한 
//	@Secured("ROLE_ADMIN")
//	@GetMapping("/info")
//	public @ResponseBody String info() {
//		return "개인정보";
//	}

	// SecuritConfig에서 preAuthorize 어노테이션 활성화: prePostEnabled = true 
	// @PreAuthorize: 해당 메서드가 실행되기 직전에 실행 
	// 여러개 걸고 싶을 떄 hasRole 사용 
//	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_ADMIN')")
//	@GetMapping("/data")
//	public @ResponseBody String data() {
//		return "데이터";
//	}
//	

	
//	@GetMapping("/admin")
//	public @ResponseBody String admin() {
//		return "admin";
//	}

}