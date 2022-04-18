package multi.fclass.iMint.transaction.service;

import java.util.List;

import multi.fclass.iMint.transaction.dto.TransactionChatroomDTO;

/**
 * @author GhostFairy
 *
 */
public interface ITransactionService {

	public String checkReservation(String myId, int goodsId);
	
	public String checkTransaction(String myId, int goodsId);

	public List<TransactionChatroomDTO> getChatroomList(int goodsId);

	public int makeReservation(int chatroomId);

	public int cancelReservation(int chatroomId);
	
	public int completeTransaction(String buyerId, int goodsId);
	
	public int addBuyerTransaction(String buyerId, int goodsId);

}