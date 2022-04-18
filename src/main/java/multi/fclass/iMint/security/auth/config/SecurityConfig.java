package multi.fclass.iMint.security.auth.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.AllArgsConstructor;
import multi.fclass.iMint.security.service.CustomOAuth2UserService;

/**
 * @author Junming, Yang
 *
 */

@AllArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터가 스프링 필터체인에 등록이 된다.
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화 
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
    private final CustomOAuth2UserService customOAuth2UserService;
	
    // login 페이지 변경

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf: 페이지 위변조 방지. 유니크 키값 토큰넣어줌. form태그 안에 넣어주는 메서드 찾기. 비동기할 때도 참고.
			.exceptionHandling()
			.accessDeniedPage("/err/denied-page"); // 접근 불가 페이지 (나중에 핸들러로 바꿀 수도)
		
		
		http.authorizeRequests()
			.antMatchers("/", "/main", "/login/**", "/static/**").permitAll() // 비로그인시 첫화면, 둘러보기만 허용

			// 아이 회원가입(test)
			.antMatchers("/register/child/test").access("hasRole('ROLE_uncerti_CHILD') or hasRole('ROLE_ADMIN')")
      
			.antMatchers("/register/**").access("hasRole('ROLE_uncerti_GUARD') or hasRole('ROLE_uncerti_CHILD') or hasRole('ROLE_ADMIN')")

			.antMatchers("/mypage/location").access("hasRole('ROLE_GUARD') or hasRole('ROLE_uncerti_GUARD')") /* or hasRole('ROLE_ADMIN') */
			.antMatchers("/mypage/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD')") // or hasRole('ROLE_ADMIN')
			
			.anyRequest().permitAll(); // 임시 허용
			
//			.antMatchers("/goods/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD')") //  or hasRole('ROLE_ADMIN')
//			.antMatchers("/chat/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD')") //  or hasRole('ROLE_ADMIN') // 뷰 
//			.antMatchers("/chatting/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD')") // 웹소켓 

//			
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인한 admin만 들어올 수 있다.
            
//			.anyRequest().denyAll(); // // 허용가능 외 나머지는 인증 받아야 접근 가능(접근불가) 
			
//		http.formLogin()
//			.loginPage("/login") // 권한이 없는 페이지로 가려고 하면 login페이지로 보내기
//			.loginProcessingUrl("/"); // /login이 호출되면 시큐리티가 낚아채셔 대신 로그인 진행 
//			.defaultSuccessUrl("/"); // 로그인하면 메인페이지로 이동.
		
		http.logout()
			.logoutSuccessUrl("/");
		
		http.oauth2Login()
			.defaultSuccessUrl("/") // oauth2 로그인
//			.loginPage("/login")  // 권한이 없는 페이지로 가려고 하면 첫화면 페이지로 보내기
		// 구글 로그인이 완료된 이후의 후처리 필요 (구글과 같은 Oauth provider를 사용하면 아래의 1번은 생략되어 편리)		// 1. 코드받기(사용자가 정상적인 로그인한 사용자임을 인증) 2. 엑세스 토큰 받기(사이트 사용자에 접근할 수 있는 권한 생김) 
		// 3. 사용자 프로필 정보를 가져옴 4. 그 정보로 자동으로 회원가입 진행 가능
			.userInfoEndpoint() //oauth2Login 성공 이후의 설정을 시작
			.userService(customOAuth2UserService); // 들어가야 할 타입이 OAuth2UserService
		
		http.sessionManagement()
			.invalidSessionUrl("/") // 유효하지 않은 세션 접근시 보낼 URL
			.maximumSessions(1) // 중복 로그인 방지 
			.maxSessionsPreventsLogin(false);
		
		http.sessionManagement()
			.sessionFixation()
			.migrateSession(); // 인증이 됐을때 새로운 세션을 생성한뒤, 기존 세션의 attribute들을 복사해 온다.
	}
	
	@Override
	   public void configure(WebSecurity web) throws Exception {
	      web.ignoring()
	         .mvcMatchers("/static/**")
	         .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	   }
	
	// 세션 변경
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
}
