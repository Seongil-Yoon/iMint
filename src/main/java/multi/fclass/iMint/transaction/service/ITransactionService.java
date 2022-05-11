package multi.fclass.iMint.transaction.service;

import java.util.List;

import multi.fclass.iMint.transaction.dto.TransactionChatroomDTO;

/**
 * @author GhostFairy
 *
 */
public interface ITransactionService {

	public int getTransaction(String myId, int goodsId);

	public String checkTransaction(String myId, int goodsId);

	public String checkTransaction(String myId, String opponentId, int goodsId);

	public List<TransactionChatroomDTO> getChatroomList(int goodsId);

	public boolean makeReservation(int chatroomId);

	public boolean cancelReservation(int chatroomId);

	public boolean completeTransaction(String buyerId, int goodsId);

	public boolean addBuyerTransaction(String buyerId, int goodsId);

}