package multi.fclass.iMint.chat.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.chat.config.ChatPrincipal;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.service.IChatService;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import net.minidev.json.JSONObject;

/**
 * @author GhostFairy
 *
 */
@Controller
public class ChatController {

	@Autowired
	ParseMbId parseService;

	@Autowired
	IChatService chatService;

	@Autowired
	IMypageService mypageService;

	// REST API: 채팅목록 불러오기
	@GetMapping("/chat/getchatrooms")
	@ResponseBody
	public List<MypageChatroomDTO> chat(Authentication auth) {
		String myId = parseService.parseMbId(auth);

		return mypageService.getChatroomList(myId, 1, 10);
	}
	
	// REST API: 메세지목록 불러오기
	@GetMapping("/chat/getchatmessages")
	@ResponseBody
	public List<ChatMessageDTO> getChatMessages(Authentication auth, int chatroomId, int pageNumber,
			int numberOfItems) {
		String myId = parseService.parseMbId(auth);

		if (!chatService.isChatroomJoinable(myId, chatroomId)) {
			return null;
		}

		return chatService.getChatroomMessages(myId, chatroomId, pageNumber, numberOfItems);
	}

	// REST API: 채팅방 생성/접속 가능한지 확인하기
	@GetMapping("/chat/check")
	@ResponseBody
	public String checkChat(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", chatService.isChatroomOpenable(myId, goodsId));

		return out.toJSONString();
	}
	
	// REST API: 채팅방 접속 위해 채팅방 번호 확인하기
	@GetMapping("/chat/start")
	@ResponseBody
	public String startChat(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("chatroomId", chatService.getChatroom(myId, goodsId));

		return out.toJSONString();
	}

	// @MessageMapping: 해당 주소로 전달된 메세지를 처리하는 메소드임을 알림
	// @SendTo: 메세지에 응답받을 경로가 별도로 정해져있지않은 경우 기본 응답 주소 지정
	@MessageMapping("/chatroom/{chatroomId}")
	@SendTo("/chat/chatroom/{chatroomId}")
	public ChatMessageDTO sendMessageChatroom(ChatPrincipal principal, ChatMessageDTO chatMessage,
			@DestinationVariable("chatroomId") Integer chatroomId) throws Exception {
		chatMessage.setMessage(chatMessage.getMessage().replace("\n", "<br>"));
		chatMessage.setChatroomId(chatroomId);
		chatMessage.setSenderId(principal.getName());
		chatMessage.setSenderNick(principal.getNick());
		chatMessage.setSendDate(LocalDateTime.now());
		chatService.sendChatMessage(chatMessage);
		return chatMessage;
	}
}
