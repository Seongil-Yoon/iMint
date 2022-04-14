package multi.fclass.iMint.wishlist.service;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.wishlist.dao.IWishlistDAO;
import multi.fclass.iMint.wishlist.dto.WishlistDTO;

/**
 * @author GhostFairy
 *
 */
@Service
public class WishlistServiceImpl implements IWishlistService {

	@Autowired
	IWishlistDAO wishlistDAO;

	@Override
	public int countWishlist(int goodsId) {
		return wishlistDAO.countWishlist(goodsId);
	}

	@Override
	public boolean checkWishlist(String myId, int goodsId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("goodsId", goodsId); // 상품 ID
		WishlistDTO wishlistDTO = wishlistDAO.checkMyWishlist(map);

		if (wishlistDTO != null && !wishlistDTO.isWishlistIsdelete()) {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때
			return true;
		} else {
			// 그 외
			return false;
		}
	}

	@Override
	public int addWishlist(String myId, int goodsId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("goodsId", goodsId); // 상품 ID
		WishlistDTO wishlistDTO = wishlistDAO.checkMyWishlist(map);

		if (wishlistDTO == null) {
			// wishlist 테이블에 등록된 적 없을 때 = INSERT
			return wishlistDAO.insertWishlist(map);
		} else if (wishlistDTO.isWishlistIsdelete()) {
			// wishlist 테이블에 등록된 적 있지만 삭제이력이 있을 때 = UPDATE
			return wishlistDAO.updateWishlist(wishlistDTO.getWishlistId());
		} else {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때 = FAILED(작업 필요 없음)
			return -1;
		}
	}

	@Override
	public int removeWishlist(String myId, int goodsId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("goodsId", goodsId); // 상품 ID
		WishlistDTO wishlistDTO = wishlistDAO.checkMyWishlist(map);

		if (wishlistDTO != null && !wishlistDTO.isWishlistIsdelete()) {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때 = UPDATE
			return wishlistDAO.removeWishlist(wishlistDTO.getWishlistId());
		} else {
			// 그 외 = FAILED(작업 필요 없음)
			return -1;
		}
	}

}
