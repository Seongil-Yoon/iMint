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
import multi.fclass.iMint.mypage.dao.IMypageDAO;
import multi.fclass.iMint.mypage.dto.MypageDTO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;


/**
 * @author haeyeon
 *
 */


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
	
//	@Autowired
//	MypageDTO mypageDTO;
	
	@Autowired
	IMypageDAO iMypageDAO;
	
// 마이페이지 - 메인
	
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
		//계정 관련 정보
		String userID = memberDTO.getMbId();
		String userNickName = memberDTO.getMbNick();
		String userEmail = memberDTO.getMbEmail();
		String userInterest = memberDTO.getMbInterest();
		String userLocation = memberDTO.getMbLocation();
		String userGuard = memberDTO.getMbGuard();
		String userPin = memberDTO.getMbPin();
		
		MemberDTO userGuardNick = securityDAO.findByMbId(userGuard);
		List<MemberDTO> userChilds = securityDAO.findByMbGuard(userID);
		
		
		mv.addObject("userID", userID);
		mv.addObject("userNickName", userNickName);
		mv.addObject("userEmail", userEmail);
		mv.addObject("userInterest", userInterest);
		mv.addObject("userLocation", userLocation);
		mv.addObject("userGuard", userGuardNick);
		mv.addObject("userPin", userPin);
		mv.addObject("userChilds", userChilds);
		
		//거래 관련 정보
		List<MypageDTO> userWish = iMypageDAO.getWishAndReserveList(mbId, 1, 5);
		mv.addObject("userWish", userWish);
		
		List<MypageDTO> userSell = iMypageDAO.getSellingList(mbId, 1, 5);
		mv.addObject("userSell", userSell);
		
		return mv;
	}
	
	
// 마이페이지 - 내 동네 설정	
	
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
	

// 마이페이지 - 나의 아이민트/내 아이 목록	
	
	@GetMapping("mypage/mylist")
	public ModelAndView indexMylist(Authentication auth) {
		ModelAndView mv = new ModelAndView();
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-mylist"); 
			
			String userID = memberDTO.getMbId();
			List<MemberDTO> userChilds = securityDAO.findByMbGuard(userID);
			mv.addObject("userChilds", userChilds);
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-myList");
			
			// 관심/구매 목록
			List<MypageDTO> userWish = iMypageDAO.getWishAndReserveList(mbId, 1, 5);
			mv.addObject("userWish", userWish);
			
			//판매 목록
			List<MypageDTO> userSell = iMypageDAO.getSellingList(mbId, 1, 5);
			mv.addObject("userSell", userSell);
		}
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

