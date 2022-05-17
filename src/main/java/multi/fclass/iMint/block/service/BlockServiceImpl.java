package multi.fclass.iMint.block.service;

import java.util.List;

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
			System.out.println("재차단");
		} else { // 최초 차단이면
			blockDAO.block(mbId, blockMbId);
			System.out.println("차단");
		}
	}

	// 차단 해제
	@Override
	public void unblock(String mbId, String unblockMbId) {
		blockDAO.unblock(mbId, unblockMbId);

	}

	// 차단여부 조회
	@Override
	public boolean blockcheck(String mbId, String blockMbId) {
		if (blockDAO.blockcheck(mbId, blockMbId) == null) {
			return false;
		} else {
			return true;
		}
	}

	// 차단목록 조회
	@Override
	public List<String> blocklist(String mbId) {
		return blockDAO.blocklist(mbId);
	}

}
