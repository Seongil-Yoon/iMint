package multi.fclass.iMint.block.service;

import java.util.List;

/**
 * @author Junming, Yang
 *
 */

public interface IBlockService {

	// 차단
	void block(String mbId, String blockMbId);

	// 차단 해제
	void unblock(String mbId, String unblockMbId);

	// 차단목록 조회
	public List<String> blocklist(String mbId);
}
