package multi.fclass.iMint.transaction.dto;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class TransactionDTO {

	private Integer id;
	private Integer goodsId;
	private String goodsTitle;
	private String sellerId;
	private String buyerId;

}
