package multi.fclass.iMint.chat.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller 
public class ChatCotroller {

	// 1. chatroom view
	@RequestMapping("/chat") public
	ModelAndView chatRoomView() {
		ModelAndView mv = new ModelAndView(); 
		mv.setViewName("chat/chatroom"); 
		return mv; 
		}
}
/*
 * package multi.fclass.iMint.chat.controller;
 * 
 * import java.io.IOException; import java.util.List; import java.util.Map;
 * 
 * import javax.servlet.http.HttpSession; import javax.xml.ws.RequestWrapper;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.messaging.handler.annotation.MessageMapping; import
 * org.springframework.messaging.simp.SimpMessagingTemplate; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.ui.Model; import
 * org.springframework.web.bind.annotation.ModelAttribute; import
 * org.springframework.web.bind.annotation.PathVariable; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.springframework.web.bind.annotation.RequestMethod;
 * 
 * import multi.fclass.iMint.chat.service.ChatServiceImpl; import
 * multi.fclass.iMint.chat.dto.ChatListInfoDTO; import
 * multi.fclass.iMint.chat.dto.ChatRoomDTO; import
 * multi.fclass.iMint.chat.dto.ChatRoomInfoDTO; import
 * multi.fclass.iMint.chat.dto.ChatMessageDTO;
 * 
 * @Controller public class ChatCotroller {
 * 
 * @Autowired private SimpMessagingTemplate simpMessage;
 * 
 * @Autowired ChatServiceImpl service;
 * 
 * // 채팅으로 거래하기 (product 상세 페이지 화면) //@RequestMapping(value="/goods/detail",
 * method = Reque) //public String getWebSocketWithSockJs(Model model,
 * HttpSession session, @ModelAttribute("chatroom") ChatRoomDTO dto) {
 * 
 * //}
 * 
 * 
 * // 0. test용 chatroom view
 * 
 * @RequestMapping(value = "/chat", method = RequestMethod.GET) public
 * ModelAndView chatRoomView(@RequestParam("chatRoomId") int chatRoomId) {
 * ModelAndView mv = new ModelAndView(); mv.setViewName("chat/chatroom"); return
 * mv; }
 * 
 * // <1> 채팅내용 저장 // 1. 최초 채팅하기 버튼 클릭시 -> 채팅방 생성 -> 채팅방 정보 db에 저장
 * 
 * @RequestMapping(value = "/chat", method = RequestMethod.POST) public
 * ModelAndView insertChatRoomInitInfoResult(ChatRoomDTO dto) { int result =
 * service.insertChatRoomInitInfo(dto); ModelAndView mv = new ModelAndView();
 * mv.addObject("result", result); // 행삽입확인용 mv.setViewName("chat/chatroom");
 * return mv; }
 * 
 * // 2. 채팅하고 뒤로가기버튼 눌렀을시 채팅메세지 정보 db에 저장
 * 
 * // <2> 채팅내용 불러오기 // 3. 조건에 따른 채팅내용 + 정보불러오기 = 채팅목록에서 채팅방 각각을 눌렀을 때 불러오는 메세지값
 * 
 * // 4. 조건에 따른 채팅내용 + 정보 불러오기 = 채팅목록에서 채팅방 눌렀을 시 불러오는 다른 값들 // 상대방닉네임(구매자인경우-
 * 판매자, 판매자인경우 - 구매자), 가격흥정여부
 * 
 * // <3> 채팅목록 불러오기 // 5. 구매자 아이디 = 입력받은 구매자 아이디 값 OR 판매자 아이디 = 입력 판매자 아이디값
 * 
 * // <4> 채팅방 삭제 // 6. 채팅목록에서 다중선택으로 채팅방 삭제
 * 
 * }
 */
