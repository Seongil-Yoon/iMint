package multi.fclass.iMint.block.service;

import java.util.List;

import multi.fclass.iMint.block.dto.BlockListDTO;

/**
 * @author Junming, Yang
 *
 */

public interface IBlockService {

	//차단 목록 조회 서비스
	List<BlockListDTO> getBlockList(String myId);

}
