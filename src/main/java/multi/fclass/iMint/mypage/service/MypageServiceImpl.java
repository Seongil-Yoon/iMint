package multi.fclass.iMint.mypage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.mypage.dao.IMypageDAO;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageChildDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;

/**
 * @author GhostFairy
 *
 */
@Service
public class MypageServiceImpl implements IMypageService {

	@Autowired
	IMypageDAO mypageDAO;

	// 내 아이 목록 조회 서비스
	@Override
	public List<MypageChildDTO> getMyChildrenList(String myId) {
		return mypageDAO.getMyChildrenList(myId);
	}

	// 관심 목록 조회 서비스
	@Override
	public List<MypageDTO> getWishAndReserveList(String myId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		return mypageDAO.getWishAndReserveList(myId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

	// 판매중 목록 조회 서비스
	@Override
	public List<MypageDTO> getSellingList(String myId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		return mypageDAO.getSellingList(myId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

	// 거래완료 목록 조회 서비스
	@Override
	public List<MypageDTO> getCompleteList(String myId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		return mypageDAO.getCompleteList(myId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

	// 채팅방 목록 조회 서비스
	@Override
	public List<MypageChatroomDTO> getChatroomList(String myId, int pageNumber, int numberOfItems) {
		if (pageNumber < 1) {
			pageNumber = 1;
		}
		if (numberOfItems < 0) {
			numberOfItems = 0;
		}

		return mypageDAO.getChatroomList(myId, (pageNumber - 1) * numberOfItems, numberOfItems);
	}

}
