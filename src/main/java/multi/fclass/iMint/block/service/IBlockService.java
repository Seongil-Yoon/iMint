package multi.fclass.iMint.block.service;

/**
 * @author Junming, Yang
 *
 */

public interface IBlockService {

	// 차단
	void block(String mbId, String blockMbId);

	// 차단 해제
	void unblock(String mbId, String unblockMbId);

}
