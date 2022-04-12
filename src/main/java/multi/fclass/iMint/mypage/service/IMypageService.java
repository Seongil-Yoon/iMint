package multi.fclass.iMint.mypage.service;

import java.util.List;

import multi.fclass.iMint.mypage.dto.CompleteListDTO;
import multi.fclass.iMint.mypage.dto.SellingListDTO;
import multi.fclass.iMint.mypage.dto.WishListDTO;

/**
 * @author GhostFairy
 *
 */
public interface IMypageService {

	// 관심 목록 조회 서비스
	public List<WishListDTO> getWishList(String myId, int pageNumber, int numberOfItems);

	// 판매중 목록 조회 서비스
	public List<SellingListDTO> getSellingList(String myId, int pageNumber, int numberOfItems);

	// 거래완료 목록 조회 서비스
	public List<CompleteListDTO> getCompleteList(String myId, int pageNumber, int numberOfItems);

}
