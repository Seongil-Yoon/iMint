package multi.fclass.iMint.chat.stomp;

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
		config.enableSimpleBroker("/topic"); // 메세지 브로커(클라이언트에게 응답 메세지를 되돌려줌) 접두사 지정
		config.setApplicationDestinationPrefixes("/app"); // @MessageMapping 어노테이션이 붙은 Controller 메소드의 주소 앞에 붙는 접두사 지정
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/gs-guide-websocket").withSockJS(); // WebSocket 통신에 실패했을 때 SockJS로 다시 시도할 주소(endpoint) 지정
	}

}
