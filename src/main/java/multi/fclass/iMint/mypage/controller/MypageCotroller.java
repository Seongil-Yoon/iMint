package multi.fclass.iMint.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @author haeyeon
 *
 */
//보호자 계정일 경우 보호자용 jsp 파일로 보여주는 로직 미구현

@Controller
public class MypageCotroller {
	@GetMapping("mypage")
	public String index() {
		return "member/baby-mypage/baby-main";
	}
	
	@GetMapping("mypage/location")
	public String indexLocation() {
		return "member/guard-mypage/guard-location";
	}
	
	@GetMapping("mypage/mylist")
	public String indexMylist() {
		return "member/baby-mypage/baby-myList";
	}
	
	@GetMapping("mypage/block")
	public String indexBlocklist() {
		return "member/baby-mypage/baby-blocklist";
	}
	
//	@GetMapping("mypage/edit")
//	public String indexEdit() {
//		return "member/baby-mypage/baby-edit";
//	}
//	
//	@GetMapping("mypage/withdraw")
//	public String indexWithdraw() {
//		return "member/baby-mypage/baby-withdraw";
//	}
}

