package multi.fclass.iMint.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

@Controller
public class ChatController {

	@Autowired
	ParseMbId parseService;

	@Autowired
	IMypageService mypageService;

	@RequestMapping("/chat")
	public ModelAndView chat(Authentication auth) {
		String myId = parseService.parseMbId(auth);
		ModelAndView mv = new ModelAndView();

		mv.addObject("ChatroomList", mypageService.getChatroomList(myId, 1, 10));
		mv.setViewName("/chat/chatroom_list");

		return mv;
	}

	@RequestMapping("/chat/chatroom")
	public ModelAndView chatroom(Authentication auth, Integer id) {
		String myId = parseService.parseMbId(auth);
		ModelAndView mv = new ModelAndView();

		mv.addObject("ChatroomList", mypageService.getChatroomList(myId, 1, 10));
		mv.setViewName("/chat/chatroom");

		return mv;
	}

	// @MessageMapping: 해당 주소로 전달된 메세지를 처리하는 메소드임을 알림
	// @SendTo: 메세지에 응답받을 경로가 별도로 정해져있지않은 경우 기본 응답 주소 지정
	@MessageMapping("/send/chatroom/{chatroomId}")
	@SendTo("/chat/chatroom/{chatroomId}")

	public ChatMessageDTO greeting(Authentication auth, ChatMessageDTO chatmessage) throws Exception {
		Thread.sleep(1000); // 서버가 작업하느라 지연되는 시간을 가정한 슬립
		chatmessage.setSenderId(parseService.parseMbId(auth));
		return chatmessage;
	}

}
