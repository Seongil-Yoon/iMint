package multi.fclass.iMint.block.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.block.dao.IBlockDAO;

/**
 * @author Jungmin, Yang
 *
 */

@Service
public class BlockServiceImpl implements IBlockService {
	@Autowired
	IBlockDAO blockDAO;

	// 차단
	@Override
	public void block(String mbId, String blockMbId) {
		if (blockDAO.blockornot(mbId, blockMbId) != null) { // 재차단이면
			blockDAO.reblock(mbId, blockMbId);
		} else { // 최초 차단이면
			blockDAO.block(mbId, blockMbId);
		}
	}

	// 차단 해제
	@Override
	public void unblock(String mbId, String unblockMbId) {
		blockDAO.unblock(mbId, unblockMbId);

	}
}
