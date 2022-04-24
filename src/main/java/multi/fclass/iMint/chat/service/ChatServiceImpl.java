package multi.fclass.iMint.chat.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.chat.dao.IChatDAO;
import multi.fclass.iMint.chat.dto.ChatCheckDTO;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;

/**
 * @author GhostFairy
 *
 */
@Service("ChatService")
public class ChatServiceImpl implements IChatService {

	@Autowired
	IChatDAO chatDAO;

	@Override
	public String getNick(String myId) {
		return chatDAO.checkMember(myId);
	}

	@Override
	public boolean isChatroomJoinable(String myId, int chatroomId) {
		if (chatDAO.checkChatJoinable(myId, chatroomId) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String isChatOpenable(String myId, int goodsId) {
		ChatCheckDTO ccDTO = chatDAO.checkChatOpenable(goodsId);
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
		if (isChatOpenable(myId, goodsId).equals("buyer")) {
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
		return chatDAO.sendChatMessage(chatMessage);
	}

	@Override
	public List<ChatMessageDTO> getChatMessages(String myId, int chatroomId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		return chatDAO.getChatMessages(chatroomId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

}
