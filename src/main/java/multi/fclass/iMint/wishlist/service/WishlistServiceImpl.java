package multi.fclass.iMint.wishlist.service;

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
	public String checkWish(String myId, int goodsId) {
		WishlistDTO wishlistDTO = wishlistDAO.checkMyWishlist(myId, goodsId);

		if (wishlistDTO == null) {
			// wishlist 테이블에 등록된 적 없을 때
			return "false";
		} else if (wishlistDTO.isDeleted()) {
			// wishlist 테이블에 등록된 적 있지만 삭제이력이 있을 때
			return "deleted";
		} else {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때
			return "true";
		}
	}

	@Override
	public String addWish(String myId, int goodsId) {
		String check = checkWish(myId, goodsId);

		if (check.equals("false")) {
			// wishlist 테이블에 등록된 적 없을 때 = INSERT
			if (wishlistDAO.insertWishlist(myId, goodsId) == 1) {
				// INSERT 성공
				return "success";
			} else {
				// INSERT 실패(알 수 없는 오류)
				return "error";
			}
		} else if (check.equals("deleted")) {
			// wishlist 테이블에 등록된 적 있지만 삭제이력이 있을 때 = UPDATE
			if (wishlistDAO.updateWishlist(myId, goodsId) == 1) {
				// UPDATE 성공
				return "success";
			} else {
				// UPDATE 실패(알 수 없는 오류)
				return "error";
			}
		} else {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때 = FAILED(작업 필요 없음)
			return "fail";
		}
	}

	@Override
	public String removeWish(String myId, int goodsId) {
		String check = checkWish(myId, goodsId);

		if (check.equals("true")) {
			// wishlist 테이블에 등록되었고 삭제되지 않았을 때 = REMOVE
			if (wishlistDAO.removeWishlist(myId, goodsId) == 1) {
				// UPDATE 성공
				return "success";
			} else {
				// UPDATE 실패(알 수 없는 오류)
				return "error";
			}
		} else {
			// 그 외 = FAILED(작업 필요 없음)
			return "fail";
		}
	}

}
