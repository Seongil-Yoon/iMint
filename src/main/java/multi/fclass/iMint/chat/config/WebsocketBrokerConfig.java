package multi.fclass.iMint.chat.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
 
@Configuration
@EnableWebSocketMessageBroker
public class WebsocketBrokerConfig implements WebSocketMessageBrokerConfigurer {
 
    private static final Logger LOGGER = LoggerFactory.getLogger(WebsocketBrokerConfig.class);
 
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        
        //for subscribe prefix
        registry.enableSimpleBroker("/user");
        //for publish prefix
        registry.setApplicationDestinationPrefixes("/app");
    }
 
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        
        registry.addEndpoint("/broadcast")
            .withSockJS()
            .setHeartbeatTime(60_000);
    }
 
}

/*WebSocket을 사용하기 위해 설정해야하는 Config 코드입니다. 
 * 두 가지 메서드를 webSocketMessageBrokerConfigurer 클래스로부터 상속(override) 받습니다. 
 * configureMessageBroker는 유저가 메시지를 전송하거나 받을 수 있도록 중간에서 
 * URL prefix(접두어)를 인식하여 올바르게 전송/전달(publish/subscribe)를 중계해주는 
 * 중개자(Broker) 역할을 합니다.
 * 
 * registerStompEndpoints는 메시지의 도착지점(endpoint)을 URL로 등록해주는 메서드입니다. 
 * 등록된 URL은 Controller의 @Messagemapping 어노테이션으로 할당해줘 
 * SimpMessagingTemplate를 통해 약속된 경로나 유저에게 메시지를 전달해줍니다.  
 * 그리고 .withSockJS() 메서드는 fallback 기능을 하는 sockJS를 할당해줍니다.
 * 
 * 마지막으로 heart-beat란 STOMP에서 TCP 연결이 잘 되어있는지 체킹하는 것인데, 
 * HTTP header를 통해 연결 상태를 주기적으로 확인합니다. 
 * setHeartbeatTime은 그 주기를 설정하는 메서드입니다.  
 * 참고로 java에서 언더바 [ _ ]를 int나 long에 천단위마다 
 * 사용하면 단위를 보기 쉽게 나타낼 수 있습니다. 
 * */


