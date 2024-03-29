package multi.fclass.iMint.block.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.block.dto.BlockDTO;

/**
 * @author Jungmin, Yang
 *
 */

@Mapper
public interface IBlockDAO {

	// 차단
	public void block(String mbId, String blockMbId);

	// 차단 해제
	public void unblock(String mbId, String unblockMbId);

	// 차단(차단 해제후 재차단)
	public void reblock(String mbId, String blockMbId);

	// 최초 차단여부 조회
	public BlockDTO blockornot(String mbId, String blockMbId);
	
	// 차단여부 조회
	public int blockcheck(String mbId, String blockMbId);
	
	// 차단목록 조회
	public List<String> blocklist(String mbId);

}
