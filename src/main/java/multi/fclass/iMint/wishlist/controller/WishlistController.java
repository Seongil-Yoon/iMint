package multi.fclass.iMint.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.jose.shaded.json.JSONObject;

import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.wishlist.service.IWishlistService;

@RestController
public class WishlistController {

	@Autowired
	IWishlistService wishlistService;

	@Autowired
	ParseMbId parseService;

	@RequestMapping("/wishlist/count")
	public String countWishes(Integer goodsId) {
		JSONObject out = new JSONObject();
		int value = wishlistService.countWishes(goodsId);

		out.put("value", value);

		return out.toJSONString();
	}

	@RequestMapping("/wishlist/check")
	public String checkWish(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();
		int check = wishlistService.checkWish(myId, goodsId);

		if (check == 1) {
			out.put("check", "true");
		} else {
			out.put("check", "false");
		}

		return out.toJSONString();
	}

	@RequestMapping("/wishlist/add")
	public String addWish(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();
		int result = wishlistService.addWish(myId, goodsId);
		int value = wishlistService.countWishes(goodsId);

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
	public String removeWish(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();
		int result = wishlistService.removeWish(myId, goodsId);
		int value = wishlistService.countWishes(goodsId);

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
