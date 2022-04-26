package multi.fclass.iMint.chat.service;

import java.util.List;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;

/**
 * @author GhostFairy
 *
 */
public interface IChatService {

	public String getNick(String myId);

	public boolean isChatroomJoinable(String myId, int chatroomId);

	public String isChatroomOpenable(String myId, int goodsId);

	public int getChatroom(String myId, int goodsId);

	public int sendChatMessage(ChatMessageDTO chatMessage);

	public List<ChatMessageDTO> getChatroomMessages(String myId, int chatroomId, int pageNumber, int numberOfItems);

}
