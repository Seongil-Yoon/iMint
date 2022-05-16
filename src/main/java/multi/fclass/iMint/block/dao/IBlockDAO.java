package multi.fclass.iMint.block.dao;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.block.dto.BlockListDTO;

import java.util.List;

/**
 * @author Junming, Yang
 *
 */

@Mapper
public interface IBlockDAO {
	

	// 차단
	public void block(String mbId, String blockmbId);
	
	// 차단 해제
	public void unblock(String mbId, String unblockmbId);
	
	// 차단 목록 조회
	public List<BlockListDTO> getBlockList(String myId);
	
}
