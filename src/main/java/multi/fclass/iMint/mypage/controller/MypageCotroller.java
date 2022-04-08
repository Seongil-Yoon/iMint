package multi.fclass.iMint.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author haeyeon
 *
 */
//보호자 계정일 경우 보호자용 jsp 파일로 보여주는 로직 미구현

@Controller
public class MypageCotroller {
	@GetMapping("/mypage")
	public String index() {
		return "../views/member/baby-mypage/baby-main";
	}
	
	@GetMapping("/mypage/mylist")
	public String indexMylist() {
		return "../views/member/baby-mypage/baby-mylist";
	}
	
	@GetMapping("/mypage/block")
	public String indexBlocklist() {
		return "../views/member/baby-mypage/baby-blocklist";
	}
	
	@GetMapping("/mypage/edit")
	public String indexEdit() {
		return "../views/member/baby-mypage/baby-edit";
	}
	
	@GetMapping("/mypage/withdraw")
	public String indexWithdraw() {
		return "../views/member/baby-mypage/baby-withdraw";
	}
}
