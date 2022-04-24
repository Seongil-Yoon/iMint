package multi.fclass.iMint.wishlist.dao;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.wishlist.dto.WishlistDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IWishlistDAO {

	public int countWishlist(int goodsId);

	public WishlistDTO checkMyWishlist(String myId, int goodsId);

	public int insertWishlist(String myId, int goodsId);

	public int updateWishlist(String myId, int goodsId);

	public int removeWishlist(String myId, int goodsId);

}
