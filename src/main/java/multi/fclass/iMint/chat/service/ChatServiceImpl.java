package multi.fclass.iMint.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.block.service.IBlockService;
import multi.fclass.iMint.chat.dao.IChatDAO;
import multi.fclass.iMint.chat.dto.ChatroomOpenCheckDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.dto.ChatroomJoinCheckDTO;

/**
 * @author GhostFairy
 *
 */
@Service("ChatService")
public class ChatServiceImpl implements IChatService {

	@Autowired
	IChatDAO chatDAO;

	@Autowired
	IMypageService mypageService;

	@Autowired
	IBlockService blockService;

	@Override
	public String getNick(String myId) {
		return chatDAO.checkMember(myId);
	}

	// 채팅방 SUBSCRIBE 또는 SEND 권한 확인
	@Override
	public boolean isChatroomJoinable(String myId, int chatroomId) {
		ChatroomJoinCheckDTO cjcDTO = chatDAO.checkChatroomJoinable(chatroomId);

		if (cjcDTO != null) {
			if (blockService.blockcheck(cjcDTO.getBuyerId(), cjcDTO.getSellerId())
					|| blockService.blockcheck(cjcDTO.getSellerId(), cjcDTO.getBuyerId())) {
				// 판매자나 구매(희망)자 중 한 명이 다른 한 명을 차단한 경우
				return false;
			} else if (cjcDTO.getSellerId().equals(myId) || cjcDTO.getBuyerId().equals(myId)) {
				// 판매자이거나 구매(희망)자인 경우
				return true;
			} else if (mypageService.isMyChild(myId, cjcDTO.getSellerId())
					|| mypageService.isMyChild(myId, cjcDTO.getBuyerId())) {
				// 판매자 또는 구매(희망)자의 보호자인 경우
				return true;
			} else {
				return false;
			}
		} else {
			// 열린 채팅방이 없는 경우
			return false;
		}
	}

	// 채팅 가능 여부(기존에 채팅 중이던 회원 또는 예약/판매완료 아닌 상품) 확인
	@Override
	public String isChatroomOpenable(String myId, int goodsId) {
		ChatroomOpenCheckDTO ccDTO = chatDAO.checkChatroomOpenable(goodsId);
		if (myId.equals(ccDTO.getSellerId())) {
			return "seller";
		} else if (ccDTO.getCompDate() != null && !myId.equals(ccDTO.getCompBuyerId())) {
			return "comp";
		} else if (ccDTO.getResrvDate() != null && !myId.equals(ccDTO.getResrvBuyerId())) {
			return "resrv";
		} else {
			return "buyer";
		}
	}

	// 이 상품에 대한 채팅방 ID 조회(기존에 대화 중이던 채팅방 또는 새 채팅방)
	@Override
	public int getChatroom(String myId, int goodsId) {
		if (isChatroomOpenable(myId, goodsId).equals("buyer")) {
			Integer chatroomId = chatDAO.getBuyerChatroom(myId, goodsId);
			if (chatroomId == null) {
				if (chatDAO.createChatroom(myId, goodsId) == 1) {
					chatroomId = chatDAO.getBuyerChatroom(myId, goodsId);
				} else {
					return -1;
				}
			}
			return chatroomId;
		} else {
			return -1;
		}
	}

	// DB에 보낸 메세지 기록
	@Override
	public int sendChatroomMessage(ChatMessageDTO chatMessage) {
		return chatDAO.sendChatroomMessage(chatMessage);
	}

	// DB에 기록된 메세지 조회
	@Override
	public List<ChatMessageDTO> getChatroomMessages(String myId, int chatroomId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		chatDAO.markAsReadAll(chatroomId, myId);
		return chatDAO.getChatroomMessages(chatroomId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

}
