package multi.fclass.iMint.security.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import multi.fclass.iMint.member.service.IMemberService;
import multi.fclass.iMint.security.service.CustomOAuth2UserService;

@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화 
public class SecurityConfig extends WebSecurityConfigurerAdapter{

    private final CustomOAuth2UserService customOAuth2UserService;
	
    public SecurityConfig(CustomOAuth2UserService customOAuth2UserService) {
        this.customOAuth2UserService = customOAuth2UserService;
    }
	
	@Autowired
	private IMemberService service;
		
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable(); // csrf: 페이지 위변조 방지. 유니크 키값 토큰넣어줌. form태그 안에 넣어주는 메서드 찾기. 비동기할 때도 참고.
		
		http.authorizeRequests()
			.antMatchers("/mypage/**").authenticated() // 이런 주소로 들어오면 인증 필요(로그인 한 사람만 들어올 수 있다)
			.antMatchers("/chat/**").authenticated()
			.antMatchers("/goods/**").authenticated()
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인한 admin만 들어올 수 있다.
			.anyRequest().permitAll(); // (위 4개 외) 다른 요청은 모두 허용. static 리소스들을 열어주는 메서드(spring.io)
		
//		http.formLogin()
//			.loginPage("/login") // 권한이 없는 페이지로 가려고 하면 login페이지로 보내기
//			.loginProcessingUrl("/login") // /login이 호출되면 시큐리티가 낚아채셔 대신 로그인 진행 
//			.defaultSuccessUrl("/"); // 로그인하면 메인페이지로 이동.
//			
		http.oauth2Login() // oauth2 로그인
			.loginPage("/login")
		// 구글 로그인이 완료된 이후의 후처리 필요 (구글과 같은 Oauth provider를 사용하면 아래의 1번은 생략되어 편리)		// 1. 코드받기(사용자가 정상적인 로그인한 사용자임을 인증) 2. 엑세스 토큰 받기(사이트 사용자에 접근할 수 있는 권한 생김) 
		// 3. 사용자 프로필 정보를 가져옴 4. 그 정보로 자동으로 회원가입 진행 가능
			.userInfoEndpoint()
			.userService(customOAuth2UserService); // 들어가야 할 타입이 OAuth2UserService
	}
}
