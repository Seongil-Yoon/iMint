package multi.fclass.iMint.block.dao;

import org.apache.ibatis.annotations.Mapper;

/**
 * @author Junming, Yang
 *
 */

@Mapper
public interface IBlockDAO {

	public void blockinsert(String mbId, String blockmbId);
	
	public void blockupdate(String allname, String mbId);
	
}
