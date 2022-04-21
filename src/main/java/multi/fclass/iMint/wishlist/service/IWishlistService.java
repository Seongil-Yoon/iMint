package multi.fclass.iMint.wishlist.service;

/**
 * @author GhostFairy
 *
 */
public interface IWishlistService {

	public int countWishes(int goodsId);

	public String checkWish(String myId, int goodsId);

	public String addWish(String myId, int goodsId);

	public String removeWish(String myId, int goodsId);

}
