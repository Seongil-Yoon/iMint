package multi.fclass.iMint.wishlist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@GetMapping("/wishlist/count")
	public String countWishes(Integer goodsId) {
		JSONObject out = new JSONObject();

		out.put("value", wishlistService.countWishes(goodsId));

		return out.toJSONString();
	}

	@GetMapping("/wishlist/check")
	public String checkWish(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("check", wishlistService.checkWish(myId, goodsId));

		return out.toJSONString();
	}

	@PostMapping("/wishlist/add")
	public String addWish(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", wishlistService.addWish(myId, goodsId));
		out.put("value", wishlistService.countWishes(goodsId));

		return out.toJSONString();
	}

	@PostMapping("/wishlist/remove")
	public String removeWish(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("result", wishlistService.removeWish(myId, goodsId));
		out.put("value", wishlistService.countWishes(goodsId));

		return out.toJSONString();
	}

}
