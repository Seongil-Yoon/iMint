package multi.fclass.iMint.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.mypage.dto.CompleteListDTO;
import multi.fclass.iMint.mypage.dto.SellingListDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IMypageDAO {

	public List<SellingListDTO> selectSellingList(Map<String, Object> params);
	public List<CompleteListDTO> selectCompleteList(Map<String, Object> params);

}
