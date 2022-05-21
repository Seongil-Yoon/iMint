package multi.fclass.iMint.admin.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;

/**
 * @author Junming, Yang
 *
 */

@ExtendWith(SpringExtension.class) // 스프링부트, JUnit사이 연결
@WebMvcTest(AdminController.class) // 컨트롤러 테스트 
@AutoConfigureMybatis // Specify instead of @MybatisTest
public class AdminCotrollerTest {
	
	@Autowired
	private WebApplicationContext context;
	
	@Autowired
	private MockMvc mvc;

	// 테스트 시작전 MockMvc 인스턴스 생성 
	@Before
	public void setup() {
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
		
		memberDTO = null;
	}
	
	Role mbRole;

	@Mock
	MemberDTO memberDTO;
	
	@Test
	@WithMockUser(roles = "ADMIN") // 인증된 가짜 유저
	@DisplayName("관리자는 회원 목록 조회에 접근 가능")
	public void loginpageTest() throws Exception {
		// given
		memberDTO.setMbRole(Role.ADMIN);
		memberDTO.setMbId("test_123456");
		
			// when
			mvc.perform(get("/admin/member"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) view().name("admin/admin_member"));
	}
	
}
