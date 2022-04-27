package multi.fclass.iMint.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.transaction.service.ITransactionService;
import net.minidev.json.JSONObject;

/**
 * @author GhostFairy
 *
 */
@RestController
public class TransactionController {

	@Autowired
	ITransactionService trxService;

	@Autowired
	ParseMbId parseService;

	@RequestMapping("transaction/resrv")
	public ModelAndView getReservationChatroomList(Authentication auth, int goodsId) {
		String myId = parseService.parseMbId(auth);
		ModelAndView mv = new ModelAndView();

		// 내가 판매자이고 예악 가능한 경우에만
		if (trxService.checkReservation(myId, goodsId).equals("?resrv")) {
			mv.addObject("chatroomList", trxService.getChatroomList(goodsId));
		}
		mv.setViewName("transaction/resrv");

		return mv;
	}

	@RequestMapping("transaction/trx")
	public ModelAndView getTransactionChatroomList(Authentication auth, String opponentId, int goodsId) {
		String myId = parseService.parseMbId(auth);
		ModelAndView mv = new ModelAndView();

		// 내가 판매자이고 거래가 완료되지 않은 경우에만
		String flag = trxService.checkTransaction(myId, opponentId, goodsId);
		if (!flag.contains("comp!") && flag.contains("seller")) {
			mv.addObject("chatroomList", trxService.getChatroomList(goodsId));
		}
		mv.setViewName("transaction/trx");

		return mv;
	}

	@RequestMapping("transaction/resrv/check")
	public String checkReservation(Authentication auth, int goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("check", trxService.checkReservation(myId, goodsId));

		return out.toJSONString();
	}

	@PostMapping("transaction/resrv/make")
	public String makeReservation(Authentication auth, int goodsId, int chatroomId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		if (trxService.checkReservation(myId, goodsId).equals("?resrv")) {
			if (trxService.makeReservation(chatroomId)) {
				out.put("result", "success");
			} else {
				out.put("result", "fail");
			}
		} else {
			out.put("result", "error");
		}

		return out.toJSONString();
	}

	@PostMapping("transaction/resrv/cancel")
	public String cancelReservation(Authentication auth, int goodsId, int chatroomId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		if (trxService.checkReservation(myId, goodsId).equals("seller")) {
			if (trxService.cancelReservation(chatroomId)) {
				out.put("result", "success");
			} else {
				out.put("result", "fail");
			}
		} else {
			out.put("result", "error");
		}

		return out.toJSONString();
	}

	@RequestMapping("transaction/trx/check")
	public String checkTransaction(Authentication auth, String opponentId, int goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("check", trxService.checkTransaction(myId, opponentId, goodsId));

		return out.toJSONString();
	}

	@PostMapping("transaction/trx/complete")
	public String completeTransaction(Authentication auth, String buyerId, int goodsId) {
		if (buyerId != null && buyerId.equals("")) {
			buyerId = null;
		}
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		// 내가 판매자이고 거래가 완료되지않은 경우에만
		String flag = trxService.checkTransaction(myId, buyerId, goodsId);
		if (!flag.contains("comp") && flag.contains("seller")) {
			if (trxService.completeTransaction(buyerId, goodsId)) {
				out.put("result", "success");
			} else {
				out.put("result", "fail");
			}
		} else {
			out.put("result", "error");
		}

		return out.toJSONString();
	}

	@PostMapping("transaction/trx/addbuyer")
	public String addBuyerTransaction(Authentication auth, String buyerId, int goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		// 내가 판매자이고 구매자가 지정되지 않은 경우에만
		String flag = trxService.checkTransaction(myId, buyerId, goodsId);
		if (flag.equals("comp?_seller")) {
			if (trxService.addBuyerTransaction(buyerId, goodsId)) {
				out.put("result", "success");
			} else {
				out.put("result", "fail");
			}
		} else {
			out.put("result", "error");
		}

		return out.toJSONString();
	}

}
