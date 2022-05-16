package multi.fclass.iMint.chat.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class ChatroomOpenCheckDTO {

	String sellerId;
	LocalDateTime resrvDate;
	String resrvBuyerId;
	LocalDateTime CompDate;
	String compBuyerId;

}
