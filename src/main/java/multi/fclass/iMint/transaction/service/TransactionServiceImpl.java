package multi.fclass.iMint.transaction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import multi.fclass.iMint.notification.service.INotificationService;
import multi.fclass.iMint.transaction.dao.ITransactionDAO;
import multi.fclass.iMint.transaction.dto.TransactionDTO;

/**
 * @author GhostFairy
 *
 */
@Service("TransactionService")
public class TransactionServiceImpl implements ITransactionService {

	@Autowired
	ITransactionDAO trxDAO;

	@Autowired
	INotificationService notifyService;

	@Override
	public int getTransaction(String myId, int goodsId) {
		TransactionDTO trxDTO = trxDAO.checkTransaction(goodsId);

		if (trxDTO == null || trxDTO.getId() == null) {
			return -1;
		} else {
			if (trxDTO.getSellerId().equals(myId) || trxDTO.getBuyerId().equals(myId)) {
				return trxDTO.getId();
			} else {
				return -1;
			}
		}
	}

	@Override
	public String checkTransaction(String myId, int goodsId) {
		return checkTransaction(myId, null, goodsId);
	}

	@Override
	public String checkTransaction(String myId, String opponentId, int goodsId) {
		TransactionDTO trxDTO = trxDAO.checkTransaction(goodsId);

		if (trxDTO == null) {
			return "error";
		}

		if (trxDTO.getId() == null) {
			trxDTO = trxDAO.checkReservation(goodsId);

			if (trxDTO == null) {
				return "error";
			}

			if (trxDTO.getId() == null) {
				if (trxDTO.getSellerId().equals(myId)) {
					// 판매중(판매자)
					return "wait_seller";
				} else {
					// 판매중(판매자 아님)
					return "wait_other";
				}
			} else {
				if (trxDTO.getSellerId().equals(myId)) {
					if (opponentId != null && trxDTO.getBuyerId().equals(opponentId)) {
						// 예약 중, 상대방이 예약자(판매자)
						return "resrv_seller_match";
					} else {
						// 예약 중(판매자)
						return "resrv_seller";
					}
				} else if (trxDTO.getBuyerId().equals(myId)) {
					// 예약 중(구매자)
					return "resrv_buyer";
				} else {
					// 예약 중(판매자/구매자 아님)
					return "resrv_other";
				}
			}
		} else {
			if (trxDTO.getSellerId().equals(myId)) {
				if (opponentId != null && trxDTO.getBuyerId().equals(opponentId)) {
					// 거래완료(판매자/구매자)
					return "comp_seller_match";
				} else {
					// 거래완료(판매자)
					return "comp_seller";
				}
			} else if (trxDTO.getBuyerId().equals(myId)) {
				// 거래완료(구매자)
				return "comp_buyer";
			} else {
				// 거래완료(판매자/구매자 아님)
				return "comp_other";
			}
		}

	}

	@Override
	public boolean makeReservation(int goodsId, int chatroomId) {
		if (trxDAO.makeReservation(chatroomId) == 2) {
			TransactionDTO trxDTO = trxDAO.checkReservation(goodsId);
			notifyService.notifyReservation(trxDTO.getBuyerId(), trxDTO.getSellerId(), trxDTO);
			notifyService.notifyReservation(trxDTO.getSellerId(), trxDTO.getBuyerId(), trxDTO);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean cancelReservation(int goodsId, int chatroomId) {
		TransactionDTO trxDTO = trxDAO.checkReservation(goodsId);
		if (trxDAO.cancelReservation(chatroomId) == 2) {
			notifyService.notifyCancelReservation(trxDTO.getBuyerId(), trxDTO.getSellerId(), trxDTO);
			notifyService.notifyCancelReservation(trxDTO.getSellerId(), trxDTO.getBuyerId(), trxDTO);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int cancelAllReservation(String myId, String blockId) {
		return trxDAO.cancelAllReservation(myId, blockId);
	}

	@Override
	public boolean completeTransaction(String buyerId, int goodsId) {
		if (trxDAO.updateGoodsStatus(goodsId) == 1) {
			if (trxDAO.completeTransaction(buyerId, goodsId) == 1) {
				TransactionDTO trxDTO = trxDAO.checkTransaction(goodsId);
				notifyService.notifyTransaction(trxDTO.getBuyerId(), trxDTO.getSellerId(), trxDTO);
				notifyService.notifyTransaction(trxDTO.getSellerId(), trxDTO.getBuyerId(), trxDTO);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

}
