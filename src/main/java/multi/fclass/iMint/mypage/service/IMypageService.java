package multi.fclass.iMint.mypage.service;

import java.util.List;

import multi.fclass.iMint.mypage.dto.CompleteListDTO;
import multi.fclass.iMint.mypage.dto.SellingListDTO;

/**
 * @author GhostFairy
 *
 */
public interface IMypageService {

	// 판매중 목록 조회 서비스
	public List<SellingListDTO> getSellingList(String myId, int pageNumber, int numberOfItems);

	// 거래완료 목록 조회 서비스
	public List<CompleteListDTO> getCompleteList(String myId, int pageNumber, int numberOfItems);

}
