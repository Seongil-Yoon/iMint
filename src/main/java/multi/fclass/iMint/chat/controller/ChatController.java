package multi.fclass.iMint.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.HtmlUtils;

import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

@Controller
public class ChatController {

	@Autowired
	ParseMbId parseService;

	@RequestMapping("/chat")
	public String chat() {
		return "/chat/chatroom";
	}
/*
	// @MessageMapping: 해당 주소로 전달된 메세지를 처리하는 메소드임을 알림
	// @SendTo: 메세지에 응답받을 경로가 별도로 정해져있지 않은 경우 기본 응답 주소 지정
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(Authentication auth, HelloMessage message) throws Exception {
		Thread.sleep(1000); // 서버가 작업하느라 지연되는 시간을 가정한 슬립
		return new Greeting(HtmlUtils.htmlEscape(parseService.parseMbId(auth) + ": " + message.getName()));
	}
*/
}
