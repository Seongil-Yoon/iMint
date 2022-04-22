package multi.fclass.iMint.mypage.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageChildDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IMypageDAO {

	public List<MypageChildDTO> getMyChildrenList(String myId);

	public List<MypageDTO> getWishAndReserveList(String myId, int startIndex, int numberOfItems);

	public List<MypageDTO> getSellingList(String myId, int startIndex, int numberOfItems);

	public List<MypageDTO> getCompleteList(String myId, int startIndex, int numberOfItems);

	public List<MypageChatroomDTO> getChatroomList(String myId, int startIndex, int numberOfItems);

}
