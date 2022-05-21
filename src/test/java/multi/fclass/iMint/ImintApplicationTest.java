package multi.fclass.iMint;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import multi.fclass.iMint.member.dao.IMemberDAO;

/**
 * @author Junming, Yang
 *
 */

@ExtendWith(SpringExtension.class) // 스프링부트, JUnit사이 연결
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT) // 통합테스트 
@AutoConfigureMockMvc
class ImintApplicationTest {

	@Autowired
	private WebApplicationContext context;
	
	private MockMvc mvc;

	// 테스트 시작전 MockMvc 인스턴스 생성 
	@BeforeEach
	public void setup() {
		System.out.println("테스트 시작전 MockMvc 인스턴스 생성");
		mvc = MockMvcBuilders
				.webAppContextSetup(context)
				.apply(springSecurity())
				.build();
	}

}
