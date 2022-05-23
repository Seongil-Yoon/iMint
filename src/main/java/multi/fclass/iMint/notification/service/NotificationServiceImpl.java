package multi.fclass.iMint.notification.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.service.IChatService;
import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.mail.service.IMailService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
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
	IChatService chatService;

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
	public boolean notifyNewMessage(ChatMessageDTO message) {
		return notify("chat", message);
	}

	@Override
	public boolean notifyNewGoods(String mbId, GoodsDTO goods) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("goods");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 새 상품을 등록했습니다: " + goods.getGoodsTitle());

		return notify("goods", dto);
	}

	@Override
	public boolean notifyReservation(String mbId, String targetId, TransactionDTO trxDTO) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 거래를 예약했습니다: " + trxDTO.getGoodsTitle());

		// 메일발송
		String targetNick = chatService.getNick(targetId);
		String guardId = mypageService.getMyGuard(mbId).getMbId();
		MemberDTO guardDto = securityDAO.findByMbId(guardId);
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
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 예약을 취소했습니다: " + trxDTO.getGoodsTitle());

		// 메일발송
		String targetNick = chatService.getNick(targetId);
		String guardId = mypageService.getMyGuard(mbId).getMbId();
		MemberDTO guardDto = securityDAO.findByMbId(guardId);
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
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 거래를 완료했습니다: " + trxDTO.getGoodsTitle());

		// 메일발송
		String targetNick = chatService.getNick(targetId);
		String guardId = mypageService.getMyGuard(mbId).getMbId();
		MemberDTO guardDto = securityDAO.findByMbId(guardId);
		GoodsDTO goodsDto = goodsDAO.goods(trxDTO.getGoodsId());
		String goodsThumbnail = goodsDAO.goodsThumbnail(trxDTO.getGoodsId()).getGoodsImagesPath();
		mailService.notiMailSend(guardDto.getMbEmail(), "[iMint]" + dto.getMessage(), targetNick, goodsDto,
				goodsThumbnail);
		// end of 메일발송

		return notify("trx", dto);
	}

}
