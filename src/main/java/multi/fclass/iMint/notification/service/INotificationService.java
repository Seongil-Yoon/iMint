package multi.fclass.iMint.notification.service;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.transaction.dto.TransactionDTO;

/**
 * @author GhostFairy
 *
 */
public interface INotificationService {

	public boolean notifyNewMessage(ChatMessageDTO message);

	public boolean notifyNewGoods(String mbId, GoodsDTO goods);

	public boolean notifyReservation(String mbId, String targetId, TransactionDTO trxDto);

	public boolean notifyCancelReservation(String mbId, String targetId, TransactionDTO trxDto);

	public boolean notifyTransaction(String mbId, String targetId, TransactionDTO trxDto);

}
