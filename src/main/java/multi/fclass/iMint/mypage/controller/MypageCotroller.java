package multi.fclass.iMint.mypage.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.mypage.dao.IMypageDAO;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageChildDTO;
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
	IMypageService mypageService;

// 마이페이지 - 메인

	@GetMapping("mypage")
	public ModelAndView index(Authentication auth) {
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		if (memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-main");
		} else if (memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-main");
		}
		// 계정 관련 정보
		String userID = memberDTO.getMbId();
		String userNickName = memberDTO.getMbNick();
		String userEmail = memberDTO.getMbEmail();
		String userInterest = memberDTO.getMbInterest();
		String userLocation = memberDTO.getMbLocation();
		String userGuard = memberDTO.getMbGuard();
		String userPin = memberDTO.getMbPin();
		String userPhoto = memberDTO.getMbThumbnail();

		MemberDTO userGuardNick = securityDAO.findByMbId(userGuard);
		List<MypageChildDTO> userChilds = mypageService.getMyChildrenList(mbId);

		mv.addObject("userID", userID);
		mv.addObject("userNickName", userNickName);
		mv.addObject("userEmail", userEmail);
		mv.addObject("userInterest", userInterest);
		mv.addObject("userLocation", userLocation);
		mv.addObject("userGuard", userGuardNick);
		mv.addObject("userPin", userPin);
		mv.addObject("userChilds", userChilds);
		mv.addObject("userPhoto", userPhoto);

		// 거래 관련 정보
		List<MypageDTO> userWish = mypageService.getWishAndReserveList(mbId);
		mv.addObject("userWish", userWish);

		List<MypageDTO> userSell = mypageService.getSellingList(mbId);
		mv.addObject("userSell", userSell);

		List<MypageDTO> userComplete = mypageService.getCompleteList(mbId);
		mv.addObject("userComplete", userComplete);

		// 구매, 판매 금액 표시
		int totalSell = 0;
		int totalBuy = 0;

		for (MypageDTO complete : userComplete) {
			if (complete.getSellerNick().equals(userNickName)) {
				totalSell += complete.getGoodsPrice();
			} else {
				totalBuy += complete.getGoodsPrice();
			}
		}

		mv.addObject("totalSell", totalSell);
		mv.addObject("totalBuy", totalBuy);

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
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId); // 보호자 본인

		memberDTO.setMbLocation(mbLocationOrGuard);

		memberDAO.updatelocation(memberDTO);

		List<MemberDTO> childs = securityDAO.findByMbGuard(mbId); // 연결된 아이

		for (MemberDTO child : childs) {
			child.setMbLocation(mbLocationOrGuard);
			memberDAO.updatelocation(child);
		}
		;

		return "redirect:/mypage";
	}

// 마이페이지 - 나의 아이민트/내 아이 목록	

	@GetMapping("mypage/mylist")
	public ModelAndView indexMylist(Authentication auth) {
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		// 보호자의 경우
		if (memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-mylist");

			List<MypageChildDTO> userChilds = mypageService.getMyChildrenList(mbId); // 닉네임 모음
			mv.addObject("userChilds", userChilds);

			// 맵으로 구현
			Map<String, List<MypageDTO>> allWish = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageDTO>> allSell = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageDTO>> allComplete = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageChatroomDTO>> allChat = new HashMap<String, List<MypageChatroomDTO>>();

			for (MypageChildDTO child : userChilds) {
				// 아이별 관심/구매 목록 불러오기

				List<MypageDTO> userWish = mypageService.getWishAndReserveList(child.getChildId());
				allWish.put(child.getChildNick(), userWish);

				// 아이별 판매 목록
				List<MypageDTO> userSell = mypageService.getSellingList(child.getChildId());
				allSell.put(child.getChildNick(), userSell);

				// 아이별 거래완료 목록
				List<MypageDTO> userComplete = mypageService.getCompleteList(child.getChildId());
				allComplete.put(child.getChildNick(), userComplete);

				// 아이별 거래완료 목록
				List<MypageChatroomDTO> userChat = mypageService.getChatroomList(child.getChildId());
				userChat.removeIf((dto) -> (dto.getMessage() == null)); // 주고 받은 메세지(마지막 메세지)가 없으면 목록에서 제외
				allChat.put(child.getChildNick(), userChat);

			}

			mv.addObject("allWish", allWish);
			mv.addObject("allSell", allSell);
			mv.addObject("allComplete", allComplete);
			mv.addObject("allChat", allChat);

		}

		// 아이의 경우
		else if (memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-myList");
			String userID = memberDTO.getMbId();
			String userNick = memberDTO.getMbNick();
			mv.addObject("userNick", userNick);

			// 관심/구매 목록
			List<MypageDTO> userWish = mypageService.getWishAndReserveList(userID);
			mv.addObject("userWish", userWish);

			// 판매 목록
			List<MypageDTO> userSell = mypageService.getSellingList(userID);
			mv.addObject("userSell", userSell);

			// 거래완료 목록
			List<MypageDTO> userComplete = mypageService.getCompleteList(userID);
			mv.addObject("userComplete", userComplete);

			// 채팅 목록
			List<MypageChatroomDTO> userChat = mypageService.getChatroomList(userID);
			userChat.removeIf((dto) -> (dto.getMessage() == null)); // 주고 받은 메세지(마지막 메세지)가 없으면 목록에서 제외
			mv.addObject("userChat", userChat);

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
