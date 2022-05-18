package multi.fclass.iMint.websocket.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import multi.fclass.iMint.websocket.interceptor.WebSocketInterceptor;

/**
 * @author GhostFairy
 *
 */
//@Configuration: 스프링 설정을 담는 클래스임을 알림
//@EnableWebSocketMessageBroker: WebSocket의 메세지 브로커를 활성화시킴
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Bean
	public WebSocketInterceptor webSocketInterceptor() {
		return new WebSocketInterceptor();
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/ws/chat", "/notify"); // 경로에 이 접두사가 붙은 메세지는 브로커(로 전달되어 클라이언트)에 전달
		config.setApplicationDestinationPrefixes("/ws/send"); // 경로에 이 접두사가 붙은 메세지는 컨트롤러에 전달
		config.setUserDestinationPrefix("/ws"); // 경로에 이 접두사가 붙은 메세지는 브로커(로 전달되어 특정 유저)에 전달
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws").withSockJS(); // WebSocket 주소(endpoint) 지정 + SockJS 지원 옵션
	}

	@Override
	public void configureClientInboundChannel(ChannelRegistration registration) {
		registration.interceptors(webSocketInterceptor());
	}

}
