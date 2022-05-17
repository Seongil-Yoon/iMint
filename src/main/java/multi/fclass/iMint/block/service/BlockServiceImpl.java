package multi.fclass.iMint.block.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.block.dao.IBlockDAO;
import multi.fclass.iMint.block.dto.BlockListDTO;


/**
 * @author Junming, Yang
 *
 */

@Service
public class BlockServiceImpl implements IBlockService {
	@Autowired
	IBlockDAO blockDAO;
	
	// 차단 
	@Override
	public void block(String mbId, String blockMbId) {
		if (blockDAO.blockornot(mbId, blockMbId) != null) { //재차단이면 
			blockDAO.reblock(mbId, blockMbId);
		}
		else { // 최초 차단이면 
			blockDAO.block(mbId, blockMbId);
		}
	}

	//	채팅방 조회 서비스
	@Override
	public List<BlockListDTO> getBlockList(String myId){
		return blockDAO.getBlockList(myId);
	}

		

}
