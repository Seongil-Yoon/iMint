package multi.fclass.iMint.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IMypageDAO {

	public List<MypageDTO> getWishList(Map<String, Object> params);

	public List<MypageDTO> getSellingList(Map<String, Object> params);

	public List<MypageDTO> getCompleteList(Map<String, Object> params);

}
