package multi.fclass.iMint.wishlist.service;

/**
 * @author GhostFairy
 *
 */
public interface IWishlistService {

	public int countWishes(int goodsId);

	public int checkWish(String myId, int goodsId);

	public int addWish(String myId, int goodsId);

	public int removeWish(String myId, int goodsId);

}
