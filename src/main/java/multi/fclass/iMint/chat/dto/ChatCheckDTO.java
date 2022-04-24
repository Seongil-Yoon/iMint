package multi.fclass.iMint.chat.dto;

import java.time.LocalDateTime;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class ChatCheckDTO {

	String sellerId;
	LocalDateTime resrvDate;
	String resrvBuyerId;
	LocalDateTime CompDate;
	String compBuyerId;

}
