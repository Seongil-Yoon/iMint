package multi.fclass.iMint.block.dao;

import org.apache.ibatis.annotations.Mapper;

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
	
}
