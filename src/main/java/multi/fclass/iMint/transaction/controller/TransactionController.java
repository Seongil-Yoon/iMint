package multi.fclass.iMint.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import multi.fclass.iMint.mypage.service.IMypageService;
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
	IMypageService mypageService;

	@Autowired
	ParseMbId parseService;

	@GetMapping("transaction/resrv/check")
	public String checkReservation(Authentication auth, int goodsId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		out.put("check", trxService.checkTransaction(myId, goodsId));

		return out.toJSONString();
	}

	@PostMapping("transaction/resrv/make")
	public String makeReservation(Authentication auth, int goodsId, int chatroomId) {
		String myId = parseService.parseMbId(auth);
		JSONObject out = new JSONObject();

		// 내가 판매자이고 예악 가능한 경우에만
		if (trxService.checkTransaction(myId, goodsId).equals("wait_seller")) {
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

		if (trxService.checkTransaction(myId, goodsId).equals("resrv_seller")) {
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

	@GetMapping("transaction/trx/check")
	public String checkTransaction(Authentication auth, String myId, String opponentId, int goodsId) {
		String authId = parseService.parseMbId(auth);
		if (myId != null && !authId.equals(myId)) {
			if (!mypageService.isMyChild(authId, myId)) {
				return null;
			}
		}
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
}
