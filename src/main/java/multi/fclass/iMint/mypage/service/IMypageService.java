package multi.fclass.iMint.mypage.service;

import java.util.List;

import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageChildDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
public interface IMypageService {

	// 내 아이 목록 조회 서비스
	public List<MypageChildDTO> getMyChildrenList(String myId);

	// 내 아이 여부 조회 서비스
	public boolean isMyChild(String myId, String childId);
	
	// 관심 목록 조회 서비스
	public List<MypageDTO> getWishAndReserveList(String myId, int pageNumber, int numberOfItems);

	// 판매중 목록 조회 서비스
	public List<MypageDTO> getSellingList(String myId, int pageNumber, int numberOfItems);

	// 거래완료 목록 조회 서비스
	public List<MypageDTO> getCompleteList(String myId, int pageNumber, int numberOfItems);

	// 채팅방 목록 조회 서비스
	public List<MypageChatroomDTO> getChatroomList(String myId, int pageNumber, int numberOfItems);

}
