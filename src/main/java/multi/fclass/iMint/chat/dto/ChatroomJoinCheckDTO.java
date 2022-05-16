package multi.fclass.iMint.chat.dto;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class ChatroomJoinCheckDTO {

	private int id; // 채팅방 ID
	private int goodsId; // 판매글 ID
	private String buyerId; // 구매자 ID
	private String sellerId; // 판매자 ID
}
