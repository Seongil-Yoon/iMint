package multi.fclass.iMint.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Override
	public String getNick(String myId) {
		return chatDAO.checkMember(myId);
	}

	@Override
	public boolean isChatroomJoinable(String myId, int chatroomId) {
		ChatroomJoinCheckDTO cjcDTO = chatDAO.checkChatroomJoinable(chatroomId);

		if (cjcDTO != null) {
			if (cjcDTO.getSellerId().equals(myId) || cjcDTO.getBuyerId().equals(myId)) {
				return true;
			} else if (mypageService.isMyChild(myId, cjcDTO.getSellerId())
					|| mypageService.isMyChild(myId, cjcDTO.getBuyerId())) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

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

	@Override
	public int sendChatMessage(ChatMessageDTO chatMessage) {
		return chatDAO.sendChatroomMessage(chatMessage);
	}

	@Override
	public List<ChatMessageDTO> getChatroomMessages(String myId, int chatroomId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		chatDAO.markAsRead(chatroomId, myId);
		return chatDAO.getChatroomMessages(chatroomId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

}
