package multi.fclass.iMint.block.service;

import java.util.List;

import multi.fclass.iMint.block.dto.BlockDTO;

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

	// 차단 단일건 조회
	public BlockDTO getOneblock(String mbId, String blockMbId);
}
