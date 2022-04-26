package multi.fclass.iMint.ai.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.ai.service.sttServiceImpl;

/**
 * @author Seongil, Yoon
 */
@Controller
public class AiController {

	@Value("${root}")
	String root;
	
	@Autowired
	sttServiceImpl sttService;

	@ResponseBody
	@PostMapping("/mp3upload")
	public String mp3upload(MultipartFile file1) throws IOException {
		// MultipartFile
		// spring mvc project - pom.xml에 디펜더시 추가
		// spring boot - 기본내장
		String fileName = null;
		String originName = file1.getOriginalFilename();
//		String path = "C:\\kdt-venture/06. AI플랫폼/강의공유/ai_images/";
		String path = root + "/iMintImage/stt/";
		String uuid = UUID.randomUUID().toString();

		SimpleDateFormat foramt1 = new SimpleDateFormat("yyyy-MM-dd_HHmmss");
		String formatToday = foramt1.format(new Date());

		fileName = originName + "_" + formatToday + "(" + uuid + ")" + ".mp3";
		File target = new File(path, fileName);
		file1.transferTo(target); // StreamUtils.copy(in, out);
		System.out.println(fileName);
		return fileName;
	}

	@ResponseBody
	@GetMapping("/chatbotstt")
	public String chatbotstt(String filename) {
		return sttService.test(filename);
	}
}
