/*package multi.fclass.iMint.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.chat.dao.IChatDAO;
import multi.fclass.iMint.chat.dto.ChatListInfoDTO;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.dto.ChatRoomDTO;
import multi.fclass.iMint.chat.dto.ChatRoomInfoDTO;

@Service
public class ChatServiceImpl implements IChatService {
	
	@Autowired
	//IChatDAO dao; 
	
	// <1> 채팅내용 저장
	// 1. 최초 채팅하기 버튼 클릭시 채팅방 정보 db에 저장
	@Override
	public int insertChatRoomInitInfo(ChatRoomDTO dto) {
		return dao.insertChatRoomInitInfo(dto);
	}
	
	// 2. 채팅하고 뒤로가기버튼 눌렀을시 채팅메세지 정보 db에 저장
	@Override
	public int insertUpdateChatMessageInfo(ChatMessageDTO dto) {
		return dao.insertUpdateChatMessageInfo(dto);
	}
	
	// <2> 채팅내용 불러오기 
	// 3. 조건에 따른 채팅내용 + 정보불러오기 = 채팅목록에서 채팅방 각각을 눌렀을 때 불러오는 메세지값
	@Override
	public ChatMessageDTO bringChatMessage(int chatRoomId) {
		return dao.bringChatMessage(chatRoomId);
	}
	
	// 4. 조건에 따른 채팅내용 + 정보 불러오기 = 채팅목록에서 채팅방 눌렀을 시 불러오는 다른 값들
	// 상대방닉네임(구매자인경우- 판매자, 판매자인경우 - 구매자), 가격흥정여부
	@Override
	public ChatRoomInfoDTO bringChatRoomInfo(int chatRoomId) {
		return dao.bringChatRoomInfo(chatRoomId);
	}
	
	// <3> 채팅목록 불러오기
	// 5. 구매자 아이디 = 입력받은 구매자 아이디 값 OR 판매자 아이디 = 입력 판매자 아이디값 
	@Override
	public List<ChatListInfoDTO> bringChatList(String buyerId, String sellerId) {
		return dao.bringChatList(buyerId, sellerId);
	}

	// <4> 채팅방 삭제
	// 6. 채팅목록에서 다중선택으로 채팅방 삭제
	@Override
	public List<ChatRoomDTO> deleteChatRoom(int[] chatRoomId) {
		return dao.deleteChatRoom(chatRoomId);
	}










}*/
