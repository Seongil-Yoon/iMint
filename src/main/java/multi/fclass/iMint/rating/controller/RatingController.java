package multi.fclass.iMint.rating.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.nimbusds.jose.shaded.json.JSONObject;

import multi.fclass.iMint.rating.dto.RatingDTO;
import multi.fclass.iMint.rating.service.IRatingService;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.transaction.service.ITransactionService;

/**
 * @author GhostFairy
 *
 */
@Controller
public class RatingController {

	@Autowired
	ParseMbId parseService;

	@Autowired
	IRatingService ratingService;

	@Autowired
	ITransactionService trxService;

	@GetMapping("/transaction/rating")
	public ModelAndView ratingPopup(Authentication auth, Integer goodsId) {
		String myId = parseService.parseMbId(auth);
		ModelAndView mv = new ModelAndView();

		if (goodsId != null) {
			// 거래가 완료되었고 거래 당사자인지 확인
			int trxId = trxService.getTransaction(myId, goodsId);
			if (trxId != -1) {
				// 평가 정보 받아오기
				RatingDTO ratingDTO = ratingService.getRatingInfo(myId, trxId);
				mv.addObject("rinfo", ratingDTO);
				if (ratingDTO.getRatingId() == null) {
					// 아직 평가하지 않음
					mv.addObject("status", "!rated");
				} else {
					// 이미 평가함
					mv.addObject("status", "rated");
				}
			} else {
				// 거래가 완료되지 않았거나 거래 당사자가 아님
				mv.addObject("status", "!permit");
			}
		} else {
			// 상품 ID가 입력되지 않음
			mv.addObject("status", "error");
		}

		mv.setViewName("trx/rating");
		return mv;
	}

	@PostMapping("/transaction/rating/make")
	@ResponseBody
	public String ratingMake(Authentication auth, Integer trxId, Integer ratingScore) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		if (trxId == null || ratingScore == null) {
			out.put("result", "error");
		} else {
			if (ratingService.makeRating(myId, trxId, ratingScore)) {
				out.put("result", "success");
			} else {
				out.put("result", "fail");
			}
		}

		return out.toJSONString();
	}

}
