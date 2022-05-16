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
	
//	채팅방 조회 서비스
	@Override
	public List<BlockListDTO> getBlockList(String myId){
		return blockDAO.getBlockList(myId);
	}

}
