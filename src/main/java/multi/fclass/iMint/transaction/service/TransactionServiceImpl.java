package multi.fclass.iMint.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.transaction.dao.ITransactionDAO;
import multi.fclass.iMint.transaction.dto.TransactionChatroomDTO;
import multi.fclass.iMint.transaction.dto.TransactionCheckDTO;

/**
 * @author GhostFairy
 *
 */
@Service("TransactionService")
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	ITransactionDAO trxDAO;

	@Override
	public String checkReservation(String myId, int goodsId) {
		TransactionCheckDTO trxCheckDTO = trxDAO.checkReservation(goodsId);

		if (trxCheckDTO == null) {
			// 예약 중 아님
			return "!resrv";
		} else if (trxCheckDTO.getSellerId().equals(myId)) {
			// 예약 중이고 현재 사용자는 판매자
			return "seller";
		} else if (trxCheckDTO.getBuyerId().equals(myId)) {
			// 내가 예약 중
			return "buyer";
		} else {
			// 다른 사용자가 예약 중
			return "resrv";
		}
	}

	@Override
	public String checkTransaction(String myId, int goodsId) {
		TransactionCheckDTO trxCheckDTO = trxDAO.checkTransaction(goodsId);

		if (trxCheckDTO.getSellerId().equals(myId)) {
			if (trxCheckDTO.getId() == null) {
				// 판매 완료되지 않음
				return "!comp";
			} else {
				if (trxCheckDTO.getBuyerId() == null) {
					return "?comp";
				} else {
					return "comp";
				}
			}
		} else {
			return "!seller";
		}
	}

	@Override
	public List<TransactionChatroomDTO> getChatroomList(int goodsId) {
		return trxDAO.getChatroomList(goodsId);
	}

	@Override
	public int makeReservation(int chatroomId) {
		return trxDAO.makeReservation(chatroomId);
	}

	@Override
	public int cancelReservation(int chatroomId) {
		return trxDAO.cancelReservation(chatroomId);
	}

	@Override
	public int completeTransaction(String buyerId, int goodsId) {
		trxDAO.updateGoodsStatus(goodsId);
		return trxDAO.completeTransaction(buyerId, goodsId);
	}

	@Override
	public int addBuyerTransaction(String buyerId, int goodsId) {
		return trxDAO.addBuyerTransaction(buyerId, goodsId);
	}

}
