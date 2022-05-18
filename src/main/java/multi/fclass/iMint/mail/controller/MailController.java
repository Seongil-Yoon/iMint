package multi.fclass.iMint.mail.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

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


}
