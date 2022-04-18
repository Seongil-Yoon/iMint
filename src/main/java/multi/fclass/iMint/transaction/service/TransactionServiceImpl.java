package multi.fclass.iMint.transaction.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.transaction.dao.ITransactionDAO;
import multi.fclass.iMint.transaction.dto.TransactionChatroomDTO;
import multi.fclass.iMint.transaction.dto.ReservationCheckDTO;

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
		ReservationCheckDTO resrvCheckDTO = trxDAO.checkReservation(goodsId);

		if (resrvCheckDTO == null) {
			return "!resrv";
		} else if (resrvCheckDTO.getSellerId().equals(myId)) {
			return "seller";
		} else if (resrvCheckDTO.getBuyerId().equals(myId)) {
			return "buyer";
		} else {
			return "resrv";
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

}
