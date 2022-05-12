package multi.fclass.iMint.mypage.service;

import java.util.List;

import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageConnectionDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
public interface IMypageService {

	// 내 보호자 조회 서비스
	public MypageConnectionDTO getMyGuard(String myId);
	
	// 내 아이 목록 조회 서비스
	public List<MypageConnectionDTO> getMyChildrenList(String myId);

	// 내 아이 여부 조회 서비스
	public boolean isMyChild(String myId, String childId);
	
	// 관심 목록 조회 서비스
	public List<MypageDTO> getWishList(String myId);

	// 구매예약/판매중 목록 조회 서비스
	public List<MypageDTO> getTradeList(String myId);

	// 거래완료 목록 조회 서비스
	public List<MypageDTO> getCompleteList(String myId);

	// 채팅방 목록 조회 서비스
	public List<MypageChatroomDTO> getChatroomList(String myId);

}
