package multi.fclass.iMint.notification.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import multi.fclass.iMint.chat.dto.ChatMessageDTO;
import multi.fclass.iMint.chat.service.IChatService;
import multi.fclass.iMint.mail.dto.MailDTO;
import multi.fclass.iMint.mail.service.IMailService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.notification.dto.NotificationDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;

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
	
	@Autowired
	IMailService mailService;
	
	@Autowired
	ISecurityDAO securityDAO;

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
	public boolean notifyReservation(String mbId, String goodsTitle){
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 거래를 예약했습니다: " + goodsTitle);

		String guardId = mypageService.getMyGuard(mbId).getMbId();
		MemberDTO guardDto = securityDAO.findByMbId(guardId);
		// 메일발송
		HashMap<String, String> mailString = new HashMap<String, String>();
		mailString.put("goodsTitle", "상품명 : " + goodsTitle);
		String htmlContent = "<h3>" + dto.getMessage() + "</h3> <h3>" + mailString.get("goodsTitle") +"</h3><br><br> <img src='cid:sample-img'>";
		MailDTO mailDto = MailDTO.builder()
				.address(guardDto.getMbEmail())
				.title("[iMint]" + dto.getMessage())
				.build();
		mailService.fileMailSend(mailDto, htmlContent);
		// end of 메일발송

		return notify("resrv", dto);
	}

	@Override
	public boolean notifyCancelReservation(String mbId, String goodsTitle) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("resrv_cancel");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 예약을 취소했습니다: " + goodsTitle);
		
		String guardId = mypageService.getMyGuard(mbId).getMbId();
		MemberDTO guardDto = securityDAO.findByMbId(guardId);
		// 메일발송
		HashMap<String, String> mailString = new HashMap<String, String>();
		mailString.put("goodsTitle", "상품명 : " + goodsTitle);
		String htmlContent = "<h3>" + dto.getMessage() + "</h3> <h3>" + mailString.get("goodsTitle") +"</h3><br><br> <img src='cid:sample-img'>";
		MailDTO mailDto = MailDTO.builder()
				.address(guardDto.getMbEmail())
				.title("[iMint]" + dto.getMessage())
				.build();
		mailService.fileMailSend(mailDto, htmlContent);
		// end of 메일발송

		return notify("resrv", dto);
	}

	@Override
	public boolean notifyTransaction(String mbId, String goodsTitle) {
		NotificationDTO dto = new NotificationDTO();

		dto.setCategory("trx");
		dto.setMbId(mbId);
		dto.setMbNick(chatService.getNick(mbId));
		dto.setMessage("내 아이 " + dto.getMbNick() + "님이 거래를 완료했습니다: " + goodsTitle);
		
		String guardId = mypageService.getMyGuard(mbId).getMbId();
		MemberDTO guardDto = securityDAO.findByMbId(guardId);
		// 메일발송
		HashMap<String, String> mailString = new HashMap<String, String>();
		mailString.put("goodsTitle", "상품명 : " + goodsTitle);
		String htmlContent = "<h3>" + dto.getMessage() + "</h3> <h3>" + mailString.get("goodsTitle") +"</h3><br><br> <img src='cid:sample-img'>";
		MailDTO mailDto = MailDTO.builder()
				.address(guardDto.getMbEmail())
				.title("[iMint]" + dto.getMessage())
				.build();
		mailService.fileMailSend(mailDto, htmlContent);
		// end of 메일발송

		return notify("trx", dto);
	}

	@Override
	public boolean notifyNewMessage(ChatMessageDTO message) {
		return notify("chat", message);
	}

}
