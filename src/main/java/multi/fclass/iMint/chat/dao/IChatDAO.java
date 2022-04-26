package multi.fclass.iMint.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.chat.dto.ChatCheckDTO;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IChatDAO {

	public String checkMember(String myId);

	public Integer checkChatroomJoinable(String myId, int chatroomId);

	public ChatCheckDTO checkChatroomOpenable(int goodsId);

	public Integer getBuyerChatroom(String myId, int goodsId);

	public Integer createChatroom(String myId, int goodsId);

	public Integer sendChatroomMessage(ChatMessageDTO chatMessage);

	public List<ChatMessageDTO> getChatroomMessages(int chatroomId, int startIndex, int numberOfItems);

}
