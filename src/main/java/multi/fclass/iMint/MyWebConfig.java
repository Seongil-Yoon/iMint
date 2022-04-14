package multi.fclass.iMint;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
//		registry
//		.addResourceHandler("/images/**")
//		.addResourceLocations("file:///C:/kdt-venture/06. AI플랫폼/강의공유/ai_images/");
		
	}

}
