package multi.fclass.iMint.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.shaded.json.JSONObject;

import multi.fclass.iMint.wishlist.service.IWishlistService;
import multi.fclass.iMint.wishlist.service.WishlistServiceImpl;

@RestController
public class WishlistController {

	@Autowired
	IWishlistService wishlistService = new WishlistServiceImpl();

	@RequestMapping("/wishlist/count")
	public String countWishlist(int goodsId) {
		JSONObject out = new JSONObject();
		int value = wishlistService.countWishlist(goodsId);

		out.put("value", value);

		return out.toJSONString();
	}

	@RequestMapping("/wishlist/check")
	public String checkWishlist(String myId, int goodsId) {
		JSONObject out = new JSONObject();
		int check = wishlistService.checkWishlist(myId, goodsId);

		if (check == 1) {
			out.put("check", "true");
		} else {
			out.put("check", "false");
		}

		return out.toJSONString();
	}

	@RequestMapping("/wishlist/add")
	public String addWishlist(String myId, int goodsId) {
		JSONObject out = new JSONObject();
		int result = wishlistService.addWishlist(myId, goodsId);
		int value = wishlistService.countWishlist(goodsId);

		if (result == 1) {
			out.put("result", "success");
		} else if (result == 0) {
			out.put("result", "fail");
		} else {
			out.put("result", "error");
		}
		out.put("value", value);

		return out.toJSONString();
	}

	@RequestMapping("/wishlist/remove")
	public String removeWishlist(String myId, int goodsId) {
		JSONObject out = new JSONObject();
		int result = wishlistService.removeWishlist(myId, goodsId);
		int value = wishlistService.countWishlist(goodsId);

		if (result == 1) {
			out.put("result", "success");
		} else if (result == 0) {
			out.put("result", "fail");
		} else {
			out.put("result", "error");
		}
		out.put("value", value);

		return out.toJSONString();
	}

}
