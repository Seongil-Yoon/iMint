package multi.fclass.iMint.notification.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.chat.dao.IChatDAO;
import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.dto.ChatroomJoinCheckDTO;
import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.mail.service.IMailService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.notification.dao.INotificationDAO;
import multi.fclass.iMint.notification.dto.NotificationDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.transaction.dto.TransactionDTO;

/**
 * @author GhostFairy
 * @author Seongil, Yoon
 *
 */
@Service("NofityService")
public class NotificationServiceImpl implements INotificationService {

	@Autowired
	SimpMessagingTemplate smt;

	@Autowired
	INotificationDAO notifyDAO;

	@Autowired
	IChatDAO chatDAO;

	@Autowired
	IMypageService mypageService;

	@Autowired
	IMailService mailService;

	@Autowired
	ISecurityDAO securityDAO;

	@Autowired
	IGoodsDAO goodsDAO;

	private boolean notify(String category, Object payload) {
		if (category.equals("chat")) {
			Map<String, Object> map = new HashMap<String, Object>();
			ChatroomJoinCheckDTO cjcDTO = chatDAO.checkChatroomJoinable(((ChatMessageDTO) payload).getChatroomId());
			String opponentId = null;

			map.put("type", "chat");
			map.put("content", (ChatMessageDTO) payload);

			if (((ChatMessageDTO) payload).getSenderId().equals(cjcDTO.getBuyerId())) {
				opponentId = cjcDTO.getSellerId();
			} else if (((ChatMessageDTO) payload).getSenderId().equals(cjcDTO.getSellerId())) {
				opponentId = cjcDTO.getBuyerId();
			} else {
				return false;
			}

			smt.convertAndSendToUser(opponentId, "/notify", map);
			return true;
		} else {
			Map<String, Object> map = new HashMap<String, Object>();

			notifyDAO.sendNotification((NotificationDTO) payload);
			((NotificationDTO) payload).setSendDate(LocalDateTime.now());
			map.put("type", "notification");
			map.put("content", (NotificationDTO) payload);

			smt.convertAndSendToUser(((NotificationDTO) payload).getMbId(), "/notify", map);
			return true;
		}
	}

	@Override
	public boolean notifyNewMessage(ChatMessageDTO message) {
		return notify("chat", message);
	}

	@Override
	public boolean notifyNewGoods(String mbId, GoodsDTO goods) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("goods");
		dto.setMbId(mypageService.getMyGuard(mbId).getMbId());
		dto.setMessage("내 아이 " + chatDAO.getNick(mbId) + "님이 새 상품을 등록했습니다: " + goods.getGoodsTitle());

		// 메일발송
		String targetNick = chatDAO.getNick(goods.getSellerNick());
		MemberDTO guardDto = securityDAO.findByMbId(dto.getMbId());
		String goodsThumbnail = goodsDAO.goodsThumbnail(goods.getGoodsId()).getGoodsImagesPath();
		mailService.notiMailSend(guardDto.getMbEmail(), "[iMint]" + dto.getMessage(), targetNick, goods,
				goodsThumbnail);
		// end of 메일발송
		return notify("goods", dto);
	}

	@Override
	public boolean notifyReservation(String mbId, String targetId, TransactionDTO trxDTO) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv");
		dto.setMbId(mypageService.getMyGuard(mbId).getMbId());
		dto.setMessage("내 아이 " + chatDAO.getNick(mbId) + "님이 거래를 예약했습니다: " + trxDTO.getGoodsTitle());

		// 메일발송
		String targetNick = chatDAO.getNick(targetId);
		MemberDTO guardDto = securityDAO.findByMbId(dto.getMbId());
		GoodsDTO goodsDto = goodsDAO.goods(trxDTO.getGoodsId());
		String goodsThumbnail = goodsDAO.goodsThumbnail(trxDTO.getGoodsId()).getGoodsImagesPath();
		mailService.notiMailSend(guardDto.getMbEmail(), "[iMint]" + dto.getMessage(), targetNick, goodsDto,
				goodsThumbnail);
		// end of 메일발송

		return notify("resrv", dto);
	}

	@Override
	public boolean notifyCancelReservation(String mbId, String targetId, TransactionDTO trxDTO) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv_cancel");
		dto.setMbId(mypageService.getMyGuard(mbId).getMbId());
		dto.setMessage("내 아이 " + chatDAO.getNick(mbId) + "님이 예약을 취소했습니다: " + trxDTO.getGoodsTitle());

		// 메일발송
		String targetNick = chatDAO.getNick(targetId);
		MemberDTO guardDto = securityDAO.findByMbId(dto.getMbId());
		GoodsDTO goodsDto = goodsDAO.goods(trxDTO.getGoodsId());
		String goodsThumbnail = goodsDAO.goodsThumbnail(trxDTO.getGoodsId()).getGoodsImagesPath();
		mailService.notiMailSend(guardDto.getMbEmail(), "[iMint]" + dto.getMessage(), targetNick, goodsDto,
				goodsThumbnail);
		// end of 메일발송

		return notify("resrv", dto);
	}

	@Override
	public boolean notifyTransaction(String mbId, String targetId, TransactionDTO trxDTO) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("trx");
		dto.setMbId(mypageService.getMyGuard(mbId).getMbId());
		dto.setMessage("내 아이 " + chatDAO.getNick(mbId) + "님이 거래를 완료했습니다: " + trxDTO.getGoodsTitle());

		// 메일발송
		String targetNick = chatDAO.getNick(targetId);
		MemberDTO guardDto = securityDAO.findByMbId(dto.getMbId());
		GoodsDTO goodsDto = goodsDAO.goods(trxDTO.getGoodsId());
		String goodsThumbnail = goodsDAO.goodsThumbnail(trxDTO.getGoodsId()).getGoodsImagesPath();
		mailService.notiMailSend(guardDto.getMbEmail(), "[iMint]" + dto.getMessage(), targetNick, goodsDto,
				goodsThumbnail);
		// end of 메일발송

		return notify("trx", dto);
	}

	@Override
	public List<NotificationDTO> getNotifications(String mbId) {
		return notifyDAO.getNotifications(mbId);
	}

	@Override
	public boolean markAsRead(String mbId, int id) {
		if (notifyDAO.markAsRead(mbId, id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int markAsReadAll(String mbId) {
		return notifyDAO.markAsReadAll(mbId);
	}

	@Override
	public boolean deleteNotification(String mbId, int id) {
		if (notifyDAO.deleteNotification(mbId, id) == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public int deleteAllNotifications(String mbId) {
		return notifyDAO.deleteAllNotifications(mbId);
	}

}
