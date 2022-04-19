package multi.fclass.iMint.transaction.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.transaction.service.ITransactionService;
import multi.fclass.iMint.transaction.service.TransactionServiceImpl;
import net.minidev.json.JSONObject;

/**
 * @author GhostFairy
 *
 */
@RestController
public class TransactionController {

	@Autowired
	ITransactionService trxService = new TransactionServiceImpl();

	@RequestMapping("transaction/resrv/check")
	public String checkReservation(String myId, int goodsId) {
		JSONObject out = new JSONObject();

		out.put("check", trxService.checkReservation(myId, goodsId));

		return out.toJSONString();
	}

	@RequestMapping("transaction/resrv")
	public ModelAndView getReservationChatroomList(int goodsId) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("chatroomList", trxService.getChatroomList(goodsId));
		mv.setViewName("transaction/resrvChatroom");

		return mv;
	}

	@PostMapping("transaction/resrv/make")
	public String makeReservation(int chatroomId) {
		JSONObject out = new JSONObject();

		if (trxService.makeReservation(chatroomId)) {
			out.put("resut", "success");
		} else {
			out.put("result", "fail");
		}

		return out.toJSONString();
	}

	@PostMapping("transaction/resrv/cancel")
	public String cancelReservation(int chatroomId) {
		JSONObject out = new JSONObject();

		if (trxService.cancelReservation(chatroomId)) {
			out.put("resut", "success");
		} else {
			out.put("result", "fail");
		}

		return out.toJSONString();
	}

	@RequestMapping("transaction/trx/check")
	public String checkTransaction(String myId, int goodsId) {
		JSONObject out = new JSONObject();

		out.put("check", trxService.checkTransaction(myId, goodsId));

		return out.toJSONString();
	}

	@RequestMapping("transaction/trx")
	public ModelAndView getTransactionChatroomList(int goodsId) {
		ModelAndView mv = new ModelAndView();

		mv.addObject("chatroomList", trxService.getChatroomList(goodsId));
		mv.setViewName("transaction/trxChatroom");

		return mv;
	}

	@PostMapping("transaction/trx/complete")
	public String completeTransaction(String myId, String buyerId, int goodsId) {
		if (buyerId != null && buyerId.equals("")) {
			buyerId = null;
		}

		JSONObject out = new JSONObject();

		if (trxService.completeTransaction(buyerId, goodsId)) {
			out.put("result", "success");
		} else {
			out.put("result", "fail");
		}

		return out.toJSONString();
	}

	@PostMapping("transaction/trx/addbuyer")
	public String addBuyerTransaction(String myId, String buyerId, int goodsId) {
		JSONObject out = new JSONObject();

		if (trxService.addBuyerTransaction(buyerId, goodsId)) {
			out.put("result", "success");
		} else {
			out.put("result", "fail");
		}

		return out.toJSONString();
	}

}
