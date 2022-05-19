package multi.fclass.iMint.mail.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import multi.fclass.iMint.mail.MailHandler;
import multi.fclass.iMint.mail.dto.MailDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Service
@AllArgsConstructor
public class MailServiceImpl implements IMailService {

	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "imintbaby001@gmail.com";
	private static final String FROM_PERSON = "iMint(아이민트)";

	@Override
	@Async("mailExecutor")
	public void mailSend(MailDTO mailDto) {
		try {
			MailHandler mailHandler = new MailHandler(mailSender);
			// 받는 사람
			mailHandler.setTo(mailDto.getAddress());
			// 보내는 사람
			mailHandler.setFrom(MailServiceImpl.FROM_ADDRESS, MailServiceImpl.FROM_PERSON);
			// 제목
			mailHandler.setSubject(mailDto.getTitle());
			// HTML layOut
			String htmlContent = "<p>" + mailDto.getMessage().get("msg") + "</p> <br> <h1>"+ mailDto.getMessage().get("pin") + "</h1> <br><br> <img src='cid:sample-img'>";
			mailHandler.setText(htmlContent, true);
			// 첨부파일
			// 이미지 삽입
			if(!mailDto.getFile().isEmpty() && mailDto.getFile() != null) {
//				mailHandler.setAttach(mailDto.getFile().getOriginalFilename(), mailDto.getFile());
				mailHandler.setInline("sample-img", mailDto.getFile());
			}
			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
