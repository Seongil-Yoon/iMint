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
	public boolean isRated(String myId, int trxId) {
		RatingDTO ratingDTO = ratingDAO.selectRating(myId, trxId);

		if (ratingDTO == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public boolean makeRating(String myId, int trxId, double ratingScore) {
		if (isRated(myId, trxId)) {
			return false;
		} else {
			if (ratingDAO.insertRating(myId, trxId, ratingScore) == 1) {
				return true;
			} else {
				return false;
			}
		}
	}

}
