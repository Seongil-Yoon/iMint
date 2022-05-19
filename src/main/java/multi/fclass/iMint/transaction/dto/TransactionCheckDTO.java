package multi.fclass.iMint.transaction.dto;

import lombok.Getter;

/**
 * @author GhostFairy
 *
 */
@Getter
public class TransactionCheckDTO {

	private Integer id;
	private Integer goodsId;
	private String goodsTitle;
	private String sellerId;
	private String buyerId;

}
