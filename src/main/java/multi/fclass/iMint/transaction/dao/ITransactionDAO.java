package multi.fclass.iMint.transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.transaction.dto.TransactionChatroomDTO;
import multi.fclass.iMint.transaction.dto.TransactionCheckDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface ITransactionDAO {

	public TransactionCheckDTO checkReservation(int goodsId);
	
	public TransactionCheckDTO checkTransaction(int goodsId);

	public List<TransactionChatroomDTO> getChatroomList(int goodsId);

	public int makeReservation(int chatroomId);

	public int cancelReservation(int chatroomdId);
	
	public int completeTransaction(String buyerId, int goodsId);
	
	public int addBuyerTransaction(String buyerId, int goodsId);
	
	public int updateGoodsStatus(int goodsId);

}
