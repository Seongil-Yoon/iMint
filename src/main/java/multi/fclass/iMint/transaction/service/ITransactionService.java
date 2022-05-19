package multi.fclass.iMint.transaction.service;

/**
 * @author GhostFairy
 *
 */
public interface ITransactionService {

	public int getTransaction(String myId, int goodsId);

	public String checkTransaction(String myId, int goodsId);

	public String checkTransaction(String myId, String opponentId, int goodsId);

	public boolean makeReservation(int goodsId, int chatroomId);

	public boolean cancelReservation(int goodsId, int chatroomId);

	public int cancelAllReservation(String myId, String blockId);

	public boolean completeTransaction(String buyerId, int goodsId);

}