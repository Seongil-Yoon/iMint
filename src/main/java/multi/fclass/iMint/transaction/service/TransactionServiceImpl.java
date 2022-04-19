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
			// 예약 중인 채팅방(판매자)
			return "seller";
		} else if (trxCheckDTO.getBuyerId().equals(myId)) {
			// 예약 중인 채팅방(구매자)
			return "buyer";
		} else {
			// 다른 사용자가 예약 중(구매자)
			return "resrv";
		}
	}

	@Override
	public String checkTransaction(String myId, int goodsId) {
		TransactionCheckDTO trxCheckDTO = trxDAO.checkTransaction(goodsId);

		if (trxCheckDTO.getId() == null) {
			// 판매 중
			return "!comp";
		} else {
			if (trxCheckDTO.getBuyerId() == null) {
				if (trxCheckDTO.getSellerId().equals(myId)) {
					// 거래완료, 구매자 지정안함(판매자)
					return "?seller";
				} else {
					// 거래완료, 구매자 지정안함(판매자 아님)
					return "?permit";
				}
			} else {
				if (trxCheckDTO.getSellerId().equals(myId)) {
					// 거래완료(판매자)
					return "seller";
				} else if (trxCheckDTO.getBuyerId().equals(myId)) {
					// 거래완료(구매자)
					return "buyer";
				} else {
					// 거래완료(판매자/구매자 아님)
					return "!permit";
				}
			}
		}
	}

	@Override
	public List<TransactionChatroomDTO> getChatroomList(int goodsId) {
		return trxDAO.getChatroomList(goodsId);
	}

	@Override
	public boolean makeReservation(int chatroomId) {
		if (trxDAO.makeReservation(chatroomId) == 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cancelReservation(int chatroomId) {
		if (trxDAO.cancelReservation(chatroomId) == 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean completeTransaction(String buyerId, int goodsId) {
		if (trxDAO.updateGoodsStatus(goodsId) == 1) {
			if (trxDAO.completeTransaction(buyerId, goodsId) == 1) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	@Override
	public boolean addBuyerTransaction(String buyerId, int goodsId) {
		if (trxDAO.addBuyerTransaction(buyerId, goodsId) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
