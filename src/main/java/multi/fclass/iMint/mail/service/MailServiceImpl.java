package multi.fclass.iMint.mail.service;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import multi.fclass.iMint.mail.MailHandler;
import multi.fclass.iMint.mail.dto.MailDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Service
public class MailServiceImpl implements IMailService {

	private JavaMailSender javaMailSender;

	@Override
	public void mailSend(MailDTO mailDto) {
		try {
			MailHandler mailHandler = new MailHandler(javaMailSender);
			mailHandler.setTo(mailDto.getAddress());
			mailHandler.setSubject("인증메일입니다.");
			String htmlContent = "<p>" + mailDto.getMessage() + "<p> <img src='cid:sample-img'>";
			mailHandler.setText(htmlContent, true);
			mailHandler.setAttach(mailDto.getFile().getOriginalFilename(), mailDto.getFile());
			mailHandler.setInline("sample-img", mailDto.getFile());
			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
