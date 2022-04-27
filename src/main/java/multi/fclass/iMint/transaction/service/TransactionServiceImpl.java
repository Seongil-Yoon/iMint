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
	public String checkTransaction(String myId, int goodsId) {
		return checkTransaction(myId, null, goodsId);
	}

	@Override
	public String checkTransaction(String myId, String opponentId, int goodsId) {
		TransactionCheckDTO trxCheckDTO = trxDAO.checkTransaction(goodsId);

		if (trxCheckDTO == null) {
			return "error";
		}

		if (trxCheckDTO.getId() == null) {
			trxCheckDTO = trxDAO.checkReservation(goodsId);

			if (trxCheckDTO == null) {
				return "error";
			}

			if (trxCheckDTO.getId() == null) {
				if (trxCheckDTO.getSellerId().equals(myId)) {
					// 판매중(판매자)
					return "wait_seller";
				} else {
					// 판매중(판매자 아님)
					return "wait_other";
				}
			} else {
				if (trxCheckDTO.getSellerId().equals(myId)) {
					if (opponentId != null && trxCheckDTO.getBuyerId().equals(opponentId)) {
						// 예약 중, 상대방이 예약자(판매자)
						return "resrv_seller_match";
					} else {
						// 예약 중(판매자)
						return "resrv_seller";
					}
				} else if (trxCheckDTO.getBuyerId().equals(myId)) {
					// 예약 중(구매자)
					return "resrv_buyer";
				} else {
					// 예약 중(판매자/구매자 아님)
					return "resrv_other";
				}
			}
		} else {
			if (trxCheckDTO.getBuyerId() == null) {
				if (trxCheckDTO.getSellerId().equals(myId)) {
					// 거래완료, 구매자 지정안함(판매자)
					return "comp?_seller";
				} else {
					// 거래완료, 구매자 지정안함(판매자/구매자 아님)
					return "comp_other";
				}
			} else {
				if (trxCheckDTO.getSellerId().equals(myId)) {
					if (opponentId != null && trxCheckDTO.getBuyerId().equals(opponentId)) {
						// 거래완료(판매자/구매자)
						return "comp!_seller_match";
					} else {
						// 거래완료(판매자)
						return "comp!_seller";
					}
				} else if (trxCheckDTO.getBuyerId().equals(myId)) {
					// 거래완료(구매자)
					return "comp!_buyer";
				} else {
					// 거래완료(판매자/구매자 아님)
					return "comp_other";
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
