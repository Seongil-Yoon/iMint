package multi.fclass.iMint.mail;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * @author Seongil, Yoon
 *
 */
public class MailHandler {

	private JavaMailSender sender;
	private MimeMessage message;
	private MimeMessageHelper messageHelper;

	// 생성자
	public MailHandler(JavaMailSender jSender) throws MessagingException {
		this.sender = jSender;
		message = jSender.createMimeMessage();
		messageHelper = new MimeMessageHelper(message, true, "UTF-8");
	}

	// 보내는 사람 이메일
	public void setFrom(String fromAddress, String fromPerson) throws MessagingException, UnsupportedEncodingException {
		messageHelper.setFrom(fromAddress, fromPerson);
	}

	// 받는 사람 이메일
	public void setTo(String email) throws MessagingException {
		messageHelper.setTo(email);
	}

	// 제목
	public void setSubject(String subject) throws MessagingException {
		messageHelper.setSubject(subject);
	}

	// 메일 내용
	public void setText(String text, boolean useHtml) throws MessagingException {
		messageHelper.setText(text, useHtml);
	}

	// 첨부 파일
	public void setAttach(String displayFileName, MultipartFile file) throws MessagingException {
		messageHelper.addAttachment(displayFileName, file);
	}

	// 이미지 삽입
	public void setInline(String contentId, MultipartFile file) throws MessagingException, IOException {
		messageHelper.addInline(contentId, new ByteArrayResource(file.getBytes()), "image/jpeg");
	}

	// 발송
	public void send() {
		try {
			sender.send(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
