import java.sql.Connection;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Junming, Yang
 *
 */

// mybatis 연결테스트 

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations ={"file:src/main/resources/mybatis/*.xml"})
public class DataSourceTest {

	private DataSource ds;

	@Test
	public void testConection()throws Exception{
		
		try(Connection con = ds.getConnection()){
			System.out.println(con);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
