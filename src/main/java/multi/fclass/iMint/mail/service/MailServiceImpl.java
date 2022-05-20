package multi.fclass.iMint.mail.service;

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
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import lombok.AllArgsConstructor;
import multi.fclass.iMint.mail.MailHandler;
import multi.fclass.iMint.mail.dto.MailDTO;

/**
 * @author Seongil, Yoon
 *
 */
@Service
@Async("mailExecutor")
public class MailServiceImpl implements IMailService {

	private JavaMailSender mailSender;
	private static final String FROM_ADDRESS = "imintbaby001@gmail.com";
	private static final String FROM_PERSON = "iMint(아이민트)";
	private String htmlContent = null;

	@Value("${root}")
	String root;
	@Value("${directory}")
	String directory;

	public MailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Override
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
			mailHandler.setText(htmlContent, true);
			// 첨부파일
			// 이미지 삽입
			if (!mailDto.getFile().isEmpty() && mailDto.getFile() != null) {
//				mailHandler.setAttach(mailDto.getFile().getOriginalFilename(), mailDto.getFile());
				mailHandler.setInline("sample-img", mailDto.getFile());
			}
			mailHandler.send();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fileMailSend(MailDTO mailDto, String htmlContent) {
		this.htmlContent = htmlContent;
		// 웰컴메일 발신
		InputStream input = null;
		OutputStream os = null;
		FileItem fileItem = null;
		try {
			File file = new File(root + "/" + directory + "/" + "iMint_Black.png");
			fileItem = new DiskFileItem("welcome", Files.probeContentType(file.toPath()), false, file.getName(),
					(int) file.length(), file.getParentFile());
			input = new FileInputStream(file);
			os = fileItem.getOutputStream();
			IOUtils.copy(input, os);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				input.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		MultipartFile welcomeFile = new CommonsMultipartFile(fileItem);

		mailDto.setFile(welcomeFile);
		// @Async 비동기처리
		mailSend(mailDto);
		// end of 웰컴메일
	}

}
