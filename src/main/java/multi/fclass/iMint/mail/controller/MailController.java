package multi.fclass.iMint.mail.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import multi.fclass.iMint.mail.dto.MailDTO;
import multi.fclass.iMint.mail.service.IMailService;

/**
 * @author Seongil, Yoon
 *
 */
@Controller
public class MailController {
	@Autowired
	IMailService mailService;

	@GetMapping("/mail")
	public String dispMail() {
		return "mail";
	}

	@PostMapping("/mail")
	public void execMail(MailDTO mailDto) {
		mailService.mailSend(mailDto);
	}
	
	// 웰컴메일 발신
//		File file = new File(root + "/" + directory + "/" + "hamster.png");
//		FileItem fileItem = new DiskFileItem("hamster", Files.probeContentType(file.toPath()), false,
//				file.getName(), (int) file.length(), file.getParentFile());
//		try {
//			InputStream input = new FileInputStream(file);
//			OutputStream os = fileItem.getOutputStream();
//			IOUtils.copy(input, os);
//			// Or faster..
//			// IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		MultipartFile welcomeFile = new CommonsMultipartFile(fileItem);
//		
//		HashMap<String, String> mailString = new HashMap<String, String>();
//		mailString.put("msg", memberDTO.getMbNick() + "님의 아이와 연동하시려면 아래의 PIN 번호를 아이가 회원 가입할 때 알려주세요");
//		mailString.put("pin", memberDTO.getMbPin());
//	    MailDTO mailDto = MailDTO.builder()
//	    		.address(memberDTO.getMbEmail())
//	    		.title("[iMint]" + memberDTO.getMbNick() + "님 가입해주셔서 감사합니다.")
//	    		.message(mailString)
//	    		.file(welcomeFile)
//	    		.build();
//	    mailService.mailSend(mailDto);
    // end of 웰컴메일


}