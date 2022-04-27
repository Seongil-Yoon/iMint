package multi.fclass.iMint.transaction.dto;

import lombok.AccessLevel;
import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class TransactionChatroomDTO {

	private boolean reserved;
	private int chatroomId;
	private String buyerId;
	private String buyerNick;
	@Getter(AccessLevel.NONE)
	private String mbThumbnail;

	public String getMbThumbnail() {
		if (mbThumbnail == null || mbThumbnail.equals("")) {
			return "/static/images/default-icon.jpeg";
		} else {
			return mbThumbnail;
		}
	}

}