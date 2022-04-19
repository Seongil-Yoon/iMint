package multi.fclass.iMint.mypage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.security.dao.IUserDAO;
import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;


/**
 * @author haeyeon
 *
 */
//보호자 계정일 경우 보호자용 jsp 파일로 보여주는 로직 미구현

@Controller
public class MypageCotroller {
	
	@Autowired
	User user;
	
	@Autowired
	IMemberDAO memberDAO;
	
	@Autowired
	IUserDAO userDAO;
	
	@Autowired
	ParseMbId parseMbId;
	
	@GetMapping("mypage")
	public String index() {
		return "member/baby-mypage/baby-main";
	}
	
	@GetMapping("mypage/location")
	public String indexLocation() {
		return "member/guard-mypage/guard-location";
	}
	
//	@GetMapping("mypage/mylist")
//	public String indexMylist() {
//		return "member/baby-mypage/baby-myList";
//	}
	
	@GetMapping("mypage/mylist")
	public ModelAndView indexMylist(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		
		String mbId = parseMbId.parseMbId(auth);
		User user = parseMbId.getUserMbId(mbId);
		
		if(user.getMbRole() == Role.GUARD) {
			mv.setViewName("member/baby-mypage/guard-myList"); 
		}
		else if(user.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-myList");
		}
		return mv;
	}
	
	@GetMapping("mypage/block")
	public ModelAndView indexBlocklist(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		
		String mbId = parseMbId.parseMbId(auth);
		User user = parseMbId.getUserMbId(mbId);
		
		if(user.getMbRole() == Role.GUARD) {
			mv.setViewName("member/baby-mypage/guard-blocklist"); 
		}
		else if(user.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-blocklist");
		}
		
		return mv;
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

