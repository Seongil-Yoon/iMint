package multi.fclass.iMint;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectorConfig {

//	@Bean
//	public ServletWebServerFactory servletContainer() {
//
//		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory() {
//			@Override
//			protected void postProcessContext(Context context) {
//				SecurityConstraint securityConstraint = new SecurityConstraint();
//				securityConstraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				securityConstraint.addCollection(collection);
//				context.addConstraint(securityConstraint);
//			}
//		};
//		tomcat.addAdditionalTomcatConnectors(createSslConnector());
//		return tomcat;
//	}
//
//	private Connector createSslConnector() {
//		Connector connector = null;
//		try {
//			connector = new Connector(URLEncoder.encode("org.apache.coyote.http11.Http11NioProtocol", "UTF-8"));
//			connector.setScheme("http");
//			connector.setSecure(false);
//			connector.setPort(8080);
//			connector.setRedirectPort(8443);
//			return connector;
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return connector;
//	}

}
