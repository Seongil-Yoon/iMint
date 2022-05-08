package multi.fclass.iMint.rating.service;

/**
 * @author GhostFairy
 *
 */
public interface IRatingService {

	public boolean isRated(String myId, int trxId);

	public boolean makeRating(String myId, int trxId, double ratingScore);

}
