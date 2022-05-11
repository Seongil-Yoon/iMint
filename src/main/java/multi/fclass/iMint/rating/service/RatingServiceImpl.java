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
	public RatingDTO getRatingInfo(String myId, int goodsId) {

		return ratingDAO.selectRating(myId, goodsId);
	}

	@Override
	public boolean makeRating(String myId, int trxId, int ratingScore) {
		if (ratingDAO.insertRating(myId, trxId, ratingScore) == 1) {
			System.out.println("insert OK");
			if (ratingDAO.updateMemberRating(myId, ratingScore - 3) == 1) {
				System.out.println("update OK");
				return true;
			} else {
				System.out.println("update FAIL");
				return false;
			}
		} else {
			System.out.println("insert FAIL");
			return false;
		}
	}

}