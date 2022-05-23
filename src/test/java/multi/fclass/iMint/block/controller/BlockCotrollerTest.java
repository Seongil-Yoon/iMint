package multi.fclass.iMint.block.controller;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
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
 * @author Jungmin, Yang
 *
 */

@ExtendWith(SpringExtension.class) // 스프링부트, JUnit사이 연결
@WebMvcTest(BlockController.class) // 컨트롤러 테스트 
@AutoConfigureMybatis // Specify instead of @MybatisTest
class BlockCotrollerTest {

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
	
	@Mock
	MemberDTO memberDTO;
	
	@Test
	@WithMockUser // 인증된 가짜 유저 
	public void BlockTest() throws Exception {
		
		// when
		mvc.perform(get("/"))
			.andExpect(status().isOk())
			.andExpect((ResultMatcher) view().name("index"));
		
		memberDTO = null;
	}

}
