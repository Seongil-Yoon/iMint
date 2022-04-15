package multi.fclass.iMint.wishlist.service;

/**
 * @author GhostFairy
 *
 */
public interface IWishlistService {

	public int countWishlist(int goodsId);

	public int checkWishlist(String myId, int goodsId);

	public int addWishlist(String myId, int goodsId);

	public int removeWishlist(String myId, int goodsId);

}
