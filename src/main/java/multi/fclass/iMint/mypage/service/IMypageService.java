package multi.fclass.iMint.mypage.service;

import java.util.List;

import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
public interface IMypageService {

	// 관심 목록 조회 서비스
	public List<MypageDTO> getWishList(String myId, int pageNumber, int numberOfItems);

	// 판매중 목록 조회 서비스
	public List<MypageDTO> getSellingList(String myId, int pageNumber, int numberOfItems);

	// 거래완료 목록 조회 서비스
	public List<MypageDTO> getCompleteList(String myId, int pageNumber, int numberOfItems);

}
