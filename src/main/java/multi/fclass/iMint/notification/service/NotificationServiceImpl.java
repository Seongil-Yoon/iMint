package multi.fclass.iMint.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.service.IChatService;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.notification.dto.NotificationDTO;

/**
 * @author GhostFairy
 *
 */
@Service("NofityService")
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	SimpMessagingTemplate smt;

	@Autowired
	IChatService chatService;

	@Autowired
	IMypageService mypageService;

	private boolean notify(String category, Object payload) {
		if (category.equals("chat")) {
			smt.convertAndSendToUser(((ChatMessageDTO) payload).getSenderId(), "/notify", payload);
		} else {
			String guardId = mypageService.getMyGuard(((NotificationDTO) payload).getMbId()).getMbId();
			if (guardId != null && !guardId.equals("")) {
				smt.convertAndSendToUser(guardId, "/notify", payload);
			}
		}

		return false;
	}

	@Override
	public boolean notifyReservation(String mbId, String goodsTitle) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 거래를 예약했습니다: " + goodsTitle);

		return notify("resrv", dto);
	}

	@Override
	public boolean notifyCancelReservation(String mbId, String goodsTitle) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv_cancel");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 예약을 취소했습니다: " + goodsTitle);

		return notify("resrv", dto);
	}

	@Override
	public boolean notifyTransaction(String mbId, String goodsTitle) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("trx");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 거래를 완료했습니다: " + goodsTitle);

		return notify("trx", dto);
	}

	@Override
	public boolean notifyNewMessage(ChatMessageDTO message) {
		return notify("chat", message);
	}

}
