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
	public int countWishes(int goodsId) {
		return wishlistDAO.countWishlist(goodsId);
	}

	@Override
	public int checkWish(String myId, int goodsId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("goodsId", goodsId); // 상품 ID
		WishlistDTO wishlistDTO = wishlistDAO.checkMyWishlist(map);

		if (wishlistDTO == null) {
			// wishlist 테이블에 등록된 적 없을 때
			return -1;
		} else if (wishlistDTO.isDeleted()) {
			// wishlist 테이블에 등록된 적 있지만 삭제이력이 있을 때
			return 0;
		} else {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때
			return 1;
		}
	}

	@Override
	public int addWish(String myId, int goodsId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("goodsId", goodsId); // 상품 ID
		int check = checkWish(myId, goodsId);

		if (check == -1) {
			// wishlist 테이블에 등록된 적 없을 때 = INSERT
			if (wishlistDAO.insertWishlist(map) == 1) {
				// INSERT 성공
				return 1;
			} else {
				// INSERT 실패(알 수 없는 오류)
				return -1;
			}
		} else if (check == 0) {
			// wishlist 테이블에 등록된 적 있지만 삭제이력이 있을 때 = UPDATE
			if (wishlistDAO.updateWishlist(map) == 1) {
				// UPDATE 성공
				return 1;
			} else {
				// UPDATE 실패(알 수 없는 오류)
				return -1;
			}
		} else {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때 = FAILED(작업 필요 없음)
			return 0;
		}
	}

	@Override
	public int removeWish(String myId, int goodsId) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("myId", myId); // 사용자 ID
		map.put("goodsId", goodsId); // 상품 ID
		int check = checkWish(myId, goodsId);

		if (check == 1) {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때 = REMOVE
			if (wishlistDAO.removeWishlist(map) == 1) {
				// UPDATE 성공
				return 1;
			} else {
				// UPDATE 실패(알 수 없는 오류)
				return -1;
			}
		} else {
			// 그 외 = FAILED(작업 필요 없음)
			return 0;
		}
	}

}
