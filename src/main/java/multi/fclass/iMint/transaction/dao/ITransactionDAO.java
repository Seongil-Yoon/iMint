package multi.fclass.iMint.transaction.dao;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.transaction.dto.TransactionDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface ITransactionDAO {

	public TransactionDTO checkReservation(int goodsId);

	public TransactionDTO checkTransaction(int goodsId);

	public int makeReservation(int chatroomId);

	public int cancelReservation(int chatroomdId);

	public int cancelAllReservation(String myId, String blockId);

	public int completeTransaction(String buyerId, int goodsId);

	public int updateGoodsStatus(int goodsId);

}
