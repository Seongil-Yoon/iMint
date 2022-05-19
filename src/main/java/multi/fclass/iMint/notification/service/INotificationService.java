package multi.fclass.iMint.notification.service;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.goods.dto.GoodsDTO;

/**
 * @author GhostFairy
 *
 */
public interface INotificationService {

	public boolean notifyReservation(String mbId, String goodsTitle);

	public boolean notifyCancelReservation(String mbId, String goodsTitle);

	public boolean notifyTransaction(String mbId, String goodsTitle);

	public boolean notifyNewMessage(ChatMessageDTO message);

	public boolean notifyNewGoods(GoodsDTO goods);

}
