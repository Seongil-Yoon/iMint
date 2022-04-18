package multi.fclass.iMint.transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.transaction.dto.TransactionChatroomDTO;
import multi.fclass.iMint.transaction.dto.ReservationCheckDTO;

/**
 * @author GhostFairy
 *
 */
@Mapper
public interface ITransactionDAO {

	public ReservationCheckDTO checkReservation(int goodsId);

	public List<TransactionChatroomDTO> getChatroomList(int goodsId);

	public int makeReservation(int chatroomId);

	public int cancelReservation(int chatroomdId);

}
