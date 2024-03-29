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
 * @author Jungmin, Yang
 *
 */

@AllArgsConstructor
@Configuration
@EnableWebSecurity // 스프링 시큐리티 필터를 스프링 필터체인에 등록
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true) // secured 어노테이션 활성화, preAuthorize 어노테이션 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final CustomOAuth2UserService customOAuth2UserService;

	// login 페이지 변경
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() // csrf: 페이지 위변조 방지. 유니크 키값 토큰넣어줌.
				.exceptionHandling().accessDeniedPage("/err/denied-page"); // 접근 불가 페이지

		http.authorizeRequests().antMatchers("/", "/main/**", "/login/**", "/static/**", "/logout/**").permitAll() // 비로그인시 첫화면, 메인 둘러보기만 허용
				.antMatchers("/mp3upload", "/chatbotstt").permitAll()

				// 아이 회원가입(test)
				.antMatchers("/register/child/test").access("hasRole('ROLE_uncerti_CHILD') or hasRole('ROLE_ADMIN')")
				.antMatchers("/register/complete").permitAll().antMatchers("/register/**")
				.access("hasRole('ROLE_uncerti_GUARD') or hasRole('ROLE_uncerti_CHILD') or hasRole('ROLE_ADMIN')")

				.antMatchers("/mypage/location").access("hasRole('ROLE_GUARD') or hasRole('ROLE_uncerti_GUARD') or hasRole('ROLE_ADMIN')")
				.antMatchers("/mypage/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD') or hasRole('ROLE_ADMIN')")

				.antMatchers("/goods/detail**").permitAll().antMatchers("/goods-list/**").permitAll()
				.antMatchers("/iMintImage/**").permitAll().antMatchers("/goods/**")
				.access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD') or hasRole('ROLE_ADMIN')")

				.antMatchers("/ws/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD') or hasRole('ROLE_ADMIN')") // 웹소켓
				.antMatchers("/wishlist/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD') or hasRole('ROLE_ADMIN')") // 관심
				.antMatchers("/transaction/**").access("hasRole('ROLE_GUARD') or hasRole('ROLE_CHILD') or hasRole('ROLE_ADMIN')") // 거래

				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')") // 로그인한 admin만 접근 가능
				.antMatchers("/mail**").permitAll()
				.anyRequest().authenticated();

		// 기본 로그인 해제
		http.httpBasic().disable();

		http.logout().logoutSuccessUrl("/");

		http.oauth2Login().defaultSuccessUrl("/") // oauth2 로그인
				.userInfoEndpoint() // oauth2Login 성공 이후의 설정을 시작
				.userService(customOAuth2UserService);

		http.sessionManagement().invalidSessionUrl("/") // 유효하지 않은 세션 접근시 보낼 URL
				.maximumSessions(1) // 중복 로그인 방지
				.maxSessionsPreventsLogin(false);

		http.sessionManagement().sessionFixation().migrateSession(); // 인증이 됐을 때 새로운 세션을 생성한뒤, 기존 세션의 attribute들을 복사
	}

	// 정적 파일 열기
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/favicon.ico", "/static/**", "/error", "/lib/**").mvcMatchers("/static/**")
				.requestMatchers(PathRequest.toStaticResources().atCommonLocations());
	}

	// 세션 변경
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
