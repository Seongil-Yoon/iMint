package multi.fclass.carrot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@MapperScan(basePackages = {"multi.fclass.carrot.*.dao"})
@ComponentScan(basePackages = { "multi.fclass.carrot" })
@ComponentScan
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class BabycarrotApplication {

	public static void main(String[] args) {
		SpringApplication.run(BabycarrotApplication.class, args);
	}

}
