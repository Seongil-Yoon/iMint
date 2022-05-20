package multi.fclass.iMint.security.controller;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.security.parsing.role.ParseMbRole;

@WebMvcTest // 단위테스트 
public class IndexControllerTest {

	@MockBean
	private ISecurityDAO securityDAO;
	
	@Autowired
	ParseMbRole parseMbRole;
	
	@Autowired
	ParseMbId parseMbId;	
	
	@Test
	public void test() {
		
	}

}
