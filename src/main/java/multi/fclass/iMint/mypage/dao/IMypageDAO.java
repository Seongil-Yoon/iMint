package multi.fclass.iMint.mypage.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.mypage.dto.CompleteListDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IMypageDAO {

	public List<CompleteListDTO> selectCompleteList(Map<String, Object> params);

}
