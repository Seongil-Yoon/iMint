package multi.fclass.iMint.mypage.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import multi.fclass.iMint.mypage.service.IMypageService;
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
	
	@Autowired
	IMypageService imypageService;
	
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
		List<MypageDTO> userWish = imypageService.getWishAndReserveList(mbId, 1, 5);
		mv.addObject("userWish", userWish);
		
		List<MypageDTO> userSell = imypageService.getSellingList(mbId, 1, 5);
		mv.addObject("userSell", userSell);
		
		List<MypageDTO> userComplete = imypageService.getCompleteList(mbId, 1, 5);
		mv.addObject("userComplete", userComplete);
		
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
		
		//보호자의 경우
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-mylist"); 
			
			String userID = memberDTO.getMbId();
			List<MemberDTO> userChilds = securityDAO.findByMbGuard(userID); //닉네임 모음
			System.out.println(userChilds);
			mv.addObject("userChilds", userChilds);
			
		//cnt로 구현 시도
			
//			int cnt = 0;
//			
//			for (MemberDTO child: userChilds) {
//				String dtoNick = child.getMbNick();
//				MemberDTO dto = securityDAO.findByMbNick(dtoNick);
//				String childID = dto.getMbId(); //닉네임을 ID로 변환
//				System.out.println(childID);
//				
//				//아이별 관심/구매 목록 불러오기
//				List<MypageDTO> userWish = imypageService.getWishAndReserveList(childID, 1, 5);
//				mv.addObject("cnt" + cnt, userWish); //cnt0, cnt1.. 처럼 key값 저장
//				
//				
//				System.out.println(userWish);
//				System.out.println("cnt" + cnt);
//				
//				
//				//아이별 판매 목록
//				List<MypageDTO> userSell = imypageService.getSellingList(childID, 1, 5);
//				mv.addObject("userSell", userSell);
//				
//				//아이별 거래완료 목록
//				List<MypageDTO> userComplete = imypageService.getCompleteList(childID, 1, 5);
//				mv.addObject("userComplete", userComplete);
//				
//				cnt += 1;
				
		//이중리스트 구현 시도
			List<List<MypageDTO>> allWish = new ArrayList<>();
			List<List<MypageDTO>> allSell = new ArrayList<>();
			List<List<MypageDTO>> allComplete = new ArrayList<>();
			
				for (MemberDTO child: userChilds) {
					String dtoNick = child.getMbNick();
					MemberDTO dto = securityDAO.findByMbNick(dtoNick);
					String childID = dto.getMbId(); //닉네임을 ID로 변환
					System.out.println(childID);
					
					//아이별 관심/구매 목록 불러오기
					List<MypageDTO> userWish = imypageService.getWishAndReserveList(childID, 1, 5);
					allWish.add(userWish);

					//아이별 판매 목록
					List<MypageDTO> userSell = imypageService.getSellingList(childID, 1, 5);
					allSell.add(userSell);
					
					
					//아이별 거래완료 목록
					List<MypageDTO> userComplete = imypageService.getCompleteList(childID, 1, 5);
					allComplete.add(userComplete);
				
				}

				mv.addObject("allWish", allWish);
				mv.addObject("allSell", allSell);
				mv.addObject("allComplete", allComplete);
			
			}
		
		//아이의 경우
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-myList");
			String userID = memberDTO.getMbId();
			
			// 관심/구매 목록
			List<MypageDTO> userWish = imypageService.getWishAndReserveList(userID, 1, 100);
			mv.addObject("userWish", userWish);
			
			//판매 목록
			List<MypageDTO> userSell = imypageService.getSellingList(userID, 1, 100);
			mv.addObject("userSell", userSell);
			System.out.println(userSell);
			System.out.println(userID);
			//거래완료 목록
			List<MypageDTO> userComplete = imypageService.getCompleteList(userID, 1, 100);
			mv.addObject("userComplete", userComplete);
			
			
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

