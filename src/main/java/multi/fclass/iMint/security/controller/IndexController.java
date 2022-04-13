package multi.fclass.iMint.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.security.dao.IUserDAO;


@Controller // 뷰 반환
public class IndexController {

	@Autowired
	private IUserDAO userdao;
	
	// Oauth 로그인 : authentication 객체를 OAuth2User 타입으로.
	@GetMapping("/test/oauth/login")
	public @ResponseBody String testOAuthLogin(
			Authentication authentication,
			@AuthenticationPrincipal OAuth2User oauth) { 
		System.out.println("/test/oauth/login ======");
		
		// 다운캐스팅
		OAuth2User oAuth2User = (OAuth2User)authentication.getPrincipal();
		System.out.println("authentication: " + oAuth2User.getAttributes());
		
		System.out.println("oauth2User" + oauth.getAttributes());
		
		return "OAuth 세션 정보 확인하기";
	}
	
	// 디폴트 설정으로는 스프링 시큐리티가 가로챈다.(설정을 추후 바꿔주어야 한다) -> SecudrityConfig 파일 생성 후 스프링시큐리티 디폴트 login페이지 작동안함 
	@GetMapping({"", "/"})
	public String loginForm() {
		return "member/login";
	}
	
	// 지금은 시큐리티가 인터셉트 
	@PostMapping("/login")
	public String login() {
		return "login";
	}
	
//	@GetMapping("/joinform")
//	public String joinForm() {
//		return "joinForm";
//	}
//
//	@PostMapping("/join")
//	public String join(User user) {
//		System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getEmail());
//		user.setRole("ROLE_USER");
//		String rawPassword = user.getPassword();
//		String encPassword = bCryptPasswordEncoder.encode(rawPassword); // bCryptPasswordEncoder: 비번 암호화 
//		user.setPassword(encPassword); // 인코딩된 password를 넣고 user정보를 save
//		userRepository.save(user); // 시큐리티로 로그인 불가: 패스워드가 암호화가 안되어서  -> 	BCryptPasswordEncoder로 해결 
//		return "redirect:/loginform";
//	}
	
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
//	// 일반로그인, OAuth로그인 모두 들어갈 수 있도록 PrincipalDetails 로 묶음.(분기할 필요x) 
//	@GetMapping("/user")
//	public @ResponseBody String user(@AuthenticationPrincipal PrincipalDetails principalDetails) {
//		System.out.println("principalDetails: " + principalDetails.getUser());
//		return "user";
//	}
	
//	@GetMapping("/admin")
//	public @ResponseBody String admin() {
//		return "admin";
//	}
//	
//	@GetMapping("/manager")
//	public @ResponseBody String manager() {
//		return "manager";
//	}
}
