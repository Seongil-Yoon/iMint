package multi.fclass.iMint.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.rating.dao.IRatingDAO;
import multi.fclass.iMint.rating.dto.RatingDTO;

/**
 * @author GhostFairy
 *
 */
@Service("RatingService")
public class RatingServiceImpl implements IRatingService {

	@Autowired
	IRatingDAO ratingDAO;

	@Override
	public RatingDTO getRatingInfo(String myId, int trxId) {

		return ratingDAO.selectRating(myId, trxId);
	}

	@Override
	public boolean makeRating(String myId, int trxId, int ratingScore) {
		RatingDTO ratingDTO = ratingDAO.selectRating(myId, trxId);
		if (ratingDTO != null && ratingDTO.getRatingId() == null) {
			// 평가 가능한 거래가 있고 아직 평가하지 않음
			if (ratingDAO.insertRating(myId, trxId, ratingScore) == 1) {
				// 평가 등록 성공
				if (ratingDAO.updateMemberRating(ratingDTO.getOpponentId(), ratingScore - 3) == 1) {
					// 회원 정보에 점수 반영 성공
					return true;
				} else {
					// 회원 정보에 점수 반영 실패
					return false;
				}
			} else {
				// 평가 등록 실패
				return false;
			}
		} else {
			// 평가 가능한 거래가 없거나 이미 평가함
			return false;
		}
	}

}