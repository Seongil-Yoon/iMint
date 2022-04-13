package multi.fclass.iMint.mypage.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.mypage.dao.IMypageDAO;
import multi.fclass.iMint.mypage.dto.CompleteListDTO;

/**
 * @author GhostFairy
 *
 */
@Service
public class MypageServiceImpl implements IMypageService {

	@Autowired
	IMypageDAO dao;

	// 거래완료 목록 조회 서비스
	public List<CompleteListDTO> getCompleteList(String myId, int pageNumber, int numberOfItems) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("startIndex", (pageNumber - 1) * numberOfItems); // 시작 항목 인덱스 (페이지 표시용)
		map.put("numberOfItems", numberOfItems); // 페이지 당 표시 항목 수 (페이지 표시용)

		return dao.selectCompleteList(map);
	}

}
