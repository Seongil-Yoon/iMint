package multi.fclass.iMint.security.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import javax.annotation.Resource;

import org.mockito.Mock;
import org.mybatis.spring.boot.test.autoconfigure.AutoConfigureMybatis;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import multi.fclass.iMint.security.dao.ISecurityDAO;

/**
 * @author Jungmin, Yang
 *
 */

@ExtendWith(SpringExtension.class) // 스프링부트, JUnit사이 연결
@WebMvcTest(IndexController.class) // 컨트롤러 테스트 
@AutoConfigureMybatis // Specify instead of @MybatisTest
public class IndexControllerTest {
	
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
	}
	
	Role mbRole;

	@Resource
	private ISecurityDAO securityDAO;
	
	@Mock
	MemberDTO memberDTO;
	
	@Test
	@WithMockUser // 인증된 가짜 유저 
	public void loginpageTest() throws Exception {
		
		// when
		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) view().name("index"));
		
		memberDTO = null;
	}
	
	@Test
	@WithMockUser(roles = "CHILD")
	@DisplayName("아이가 내 동네 설정시 403에러로 접근 거부")
	public void childLocationFailTest() throws Exception {
		
		// given
		memberDTO.setMbRole(Role.CHILD);
		
		// when
		mvc.perform(get("/mypage/location"))
			.andExpect(status().isForbidden()); // 403	
	}

	@Test
	@WithMockUser(roles = "GUARD")
	@DisplayName("일반 유저가 관리자 페이지 접근시 403에러로 접근 거부")
	public void userAdminFailTest() throws Exception {
		
		// given
		memberDTO.setMbRole(Role.GUARD);
		
		// when
		mvc.perform(get("/admin"))
			.andExpect(status().isForbidden()); // 403	
	}
	
}
