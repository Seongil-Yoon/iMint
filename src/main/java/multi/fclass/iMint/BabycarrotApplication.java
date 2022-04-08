package multi.fclass.iMint;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"multi.fclass.iMint.*.dao"})
@ComponentScan(basePackages = { "multi.fclass.iMint" })
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BabycarrotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabycarrotApplication.class, args);
	}

}
