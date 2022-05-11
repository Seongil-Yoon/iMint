package multi.fclass.iMint.rating.service;

import multi.fclass.iMint.rating.dto.RatingDTO;

/**
 * @author GhostFairy
 *
 */
public interface IRatingService {

	public RatingDTO getRatingInfo(String myId, int goodsId);
	
	public boolean makeRating(String myId, int trxId, int ratingScore);

}
