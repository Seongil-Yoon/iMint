package multi.fclass.iMint.mypage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.mypage.dto.MypageBlockDTO;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageConnectionDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IMypageDAO {

	public MypageConnectionDTO getMyGuard(String myId);

	public List<MypageConnectionDTO> getMyChildrenList(String myId);

	public List<MypageDTO> getWishList(String myId);

	public List<MypageDTO> getTradeList(String myId);

	public List<MypageDTO> getCompleteList(String myId);

	public List<MypageChatroomDTO> getChatroomList(String myId);

	public List<MypageBlockDTO> getBlockList(String myId);

}
