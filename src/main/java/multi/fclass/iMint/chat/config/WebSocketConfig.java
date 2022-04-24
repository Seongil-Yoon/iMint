package multi.fclass.iMint.chat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

// @Configuration: 스프링 설정을 담는 클래스임을 알림
// @EnableWebSocketMessageBroker: WebSocket의 메세지 브로커를 활성화시킴
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/chat"); // 이 접두사가 붙은 메세지는 브로커에 전달(서버->클라이언트)
		config.setApplicationDestinationPrefixes("/chat"); // 이 접두사가 붙은 메세지는 컨트롤러에 전달(클라이언트->서버)
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/chat").withSockJS(); // WebSocket 주소(endpoint) 지정 + SockJS 지원 옵션
	}

}
