package multi.fclass.iMint.mypage.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;


/**
 * @author haeyeon
 *
 */
//보호자 계정일 경우 보호자용 jsp 파일로 보여주는 로직 미구현

@Controller
public class MypageCotroller {
	
	@Autowired
	MemberDTO memberDTO;
	
	@Autowired
	IMemberDAO memberDAO;
	
	@Autowired
	ISecurityDAO securityDAO;
	
	@Autowired
	ParseMbId parseMbId;
	
	@GetMapping("mypage")
	public ModelAndView index(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-main"); 
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-main");
		}
		
		String userID = memberDTO.getMbId();
		String userNickName = memberDTO.getMbNick();
		String userEmail = memberDTO.getMbEmail();
		String userInterest = memberDTO.getMbInterest();
		String userLocation = memberDTO.getMbLocation();
		String userGuard = memberDTO.getMbGuard();
		String userPin = memberDTO.getMbPin();
		List<MemberDTO> userChilds = securityDAO.findByMbGuard(userID);
		
		
		mv.addObject("userID", userID);
		mv.addObject("userNickName", userNickName);
		mv.addObject("userEmail", userEmail);
		mv.addObject("userInterest", userInterest);
		mv.addObject("userLocation", userLocation);
		mv.addObject("userGuard", userGuard);
		mv.addObject("userPin", userPin);
		mv.addObject("userChilds", userChilds);
		return mv;
	}
	
	@GetMapping("mypage/location")
	public ModelAndView indexLocation(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		mv.addObject("memberDTO", memberDTO);
		mv.setViewName("member/guard-mypage/guard-location");
		
		return mv;
	}
	
	@PostMapping("mypage/location")
	public String indexLocationResult(Authentication auth, String mbLocationOrGuard) {
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		memberDTO.setMbLocation(mbLocationOrGuard);
		
		memberDAO.updatelocation(memberDTO);
		
		return "redirect:/mypage";
	}
	
//	@GetMapping("mypage/mylist")
//	public String indexMylist() {
//		return "member/baby-mypage/baby-myList";
//	}
	
	@GetMapping("mypage/mylist")
	public ModelAndView indexMylist(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-mylist"); 
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-mylist");
		}
		
		String userGuard = memberDTO.getMbGuard();
		List<MemberDTO> userChilds = securityDAO.findByMbGuard(userGuard);
		mv.addObject("userChilds", userChilds);
		
		return mv;
	}
	
//	@GetMapping("mypage/block")
//	public ModelAndView indexBlocklist(Authentication auth) {
//		ModelAndView mv = new ModelAndView();
//		
//		String mbId = parseMbId.parseMbId(auth);
//		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
//		
//		if(memberDTO.getMbRole() == Role.UN_GUARD) {
//			mv.setViewName("member/guard-mypage/guard-blocklist"); 
//		}
//		else if(memberDTO.getMbRole() == Role.UN_CHILD) {
//			mv.setViewName("member/baby-mypage/baby-blocklist");
//		}
//		
//		return mv;
//	}
	
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

