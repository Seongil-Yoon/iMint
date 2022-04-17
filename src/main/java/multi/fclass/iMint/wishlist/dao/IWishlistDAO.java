package multi.fclass.iMint.wishlist.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.wishlist.dto.WishlistDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface IWishlistDAO {

	public int countWishlist(int goodsId);

	public WishlistDTO checkMyWishlist(Map<String, Object> params);

	public int insertWishlist(Map<String, Object> params);

	public int updateWishlist(Map<String, Object> params);

	public int removeWishlist(Map<String, Object> params);

}
