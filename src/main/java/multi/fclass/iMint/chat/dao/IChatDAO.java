package multi.fclass.iMint.chat.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.chat.dto.ChatroomOpenCheckDTO;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.dto.ChatroomJoinCheckDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IChatDAO {

	public String getNick(String myId);

	public ChatroomJoinCheckDTO checkChatroomJoinable(int chatroomId);

	public ChatroomOpenCheckDTO checkChatroomOpenable(int goodsId);

	public Integer getBuyerChatroom(String myId, int goodsId);

	public Integer createChatroom(String myId, int goodsId);

	public Integer sendChatroomMessage(ChatMessageDTO chatMessage);

	public List<ChatMessageDTO> getChatroomMessages(int chatroomId, int startIndex, int numberOfItems);

	public int markAsReadAll(int chatroomId, String myId);

}
