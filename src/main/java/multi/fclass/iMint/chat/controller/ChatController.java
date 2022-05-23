package multi.fclass.iMint.chat.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.service.IChatService;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageConnectionDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.notification.service.INotificationService;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.websocket.config.WebSocketPrincipal;
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

	@Autowired
	INotificationService notifyService;

	// REST API: 채팅목록 불러오기
	@GetMapping("/ws/chat/getchatrooms")
	@ResponseBody
	public List<MypageChatroomDTO> getChatrooms(Authentication auth, @Nullable String childId) {
		String myId = parseService.parseMbId(auth);

		if (childId != null && !childId.equals("") && !childId.equals("null")) {
			if (mypageService.isMyChild(myId, childId)) {
				return mypageService.getChatroomList(childId);
			} else {
				return null;
			}
		} else {
			return mypageService.getChatroomList(myId);
		}
	}

	// REST API: 메세지목록 불러오기
	@GetMapping("/ws/chat/getchatmessages")
	@ResponseBody
	public List<ChatMessageDTO> getChatMessages(Authentication auth, @Nullable String childId, int chatroomId,
			int pageNumber) {
		String myId = parseService.parseMbId(auth);
		int numberOfItems = 30;

		if (childId != null && !childId.equals("")) {
			if (mypageService.isMyChild(myId, childId)) {
				return chatService.getChatroomMessages(childId, chatroomId, pageNumber, numberOfItems);
			} else {
				return null;
			}
		} else {
			// 보호자가 아닌 아이가 직접 읽을 때만 읽음 표시
			chatService.markAsReadAll(chatroomId, myId);
			return chatService.getChatroomMessages(myId, chatroomId, pageNumber, numberOfItems);
		}
	}

	// REST API: 내 아이 목록 불러오기
	@GetMapping("/ws/chat/getmychildren")
	@ResponseBody
	public List<MypageConnectionDTO> getChildren(Authentication auth) {
		String myId = parseService.parseMbId(auth);

		return mypageService.getMyChildrenList(myId);
	}

	// REST API: 채팅방 생성/접속 가능한지 확인하기
	@GetMapping("/ws/chat/checkmychatroom")
	@ResponseBody
	public String checkChatroom(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", chatService.isChatroomOpenable(myId, goodsId));

		return out.toJSONString();
	}

	// REST API: 채팅방 접속 위해 채팅방 번호 확인하기
	@PostMapping("/ws/chat/getmychatroom")
	@ResponseBody
	public String startChatrom(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("chatroomId", chatService.getChatroom(myId, goodsId));

		return out.toJSONString();
	}

	// @MessageMapping: 해당 주소로 전달된 메세지를 처리하는 메소드임을 알림
	// @SendTo: 메세지에 응답받을 경로가 별도로 정해져있지않은 경우 기본 응답 주소 지정
	@MessageMapping("/chat/{chatroomId}")
	@SendTo("/ws/chat/{chatroomId}")
	public ChatMessageDTO sendMessageChatroom(WebSocketPrincipal principal, ChatMessageDTO chatMessage,
			@DestinationVariable("chatroomId") Integer chatroomId) throws Exception {
		chatMessage.setMessage(chatMessage.getMessage().replace("\n", "<br>"));
		chatMessage.setChatroomId(chatroomId);
		chatMessage.setSenderId(principal.getName());
		chatMessage.setSenderNick(chatService.getNick(principal.getName()));
		chatMessage.setSendDate(LocalDateTime.now());
		chatService.sendChatroomMessage(chatMessage);
		notifyService.notifyNewMessage(chatMessage);
		return chatMessage;
	}

}
