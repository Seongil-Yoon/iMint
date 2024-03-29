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
import multi.fclass.iMint.mypage.dto.MypageBlockDTO;
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageConnectionDTO;
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
	IMemberDAO memberDAO;

	@Autowired
	ISecurityDAO securityDAO;

	@Autowired
	ParseMbId parseMbId;

	@Autowired
	IMypageService mypageService;

// 마이페이지 - 메인

	@GetMapping("mypage")
	public ModelAndView index(Authentication auth) {
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		Role mbRole = parseMbId.getRoleMbId(mbId);

		if (mbRole == Role.GUARD) {
			List<MypageConnectionDTO> userChilds = mypageService.getMyChildrenList(mbId);
			mv.addObject("userChilds", userChilds);
		} else if (mbRole == Role.CHILD) {
			MypageConnectionDTO guard = mypageService.getMyGuard(mbId);
			mv.addObject("guard", guard);

			// 거래 관련 정보
			List<MypageDTO> userWish = mypageService.getWishList(mbId);
			mv.addObject("userWish", userWish.size());

			List<MypageDTO> userTrade = mypageService.getTradeList(mbId);
			mv.addObject("userTrade", userTrade.size());

			List<MypageDTO> userComp = mypageService.getCompleteList(mbId);
			mv.addObject("userComp", userComp.size());

			// 구매, 판매 금액 표시
			int totalSell = 0;
			int totalBuy = 0;
			for (MypageDTO complete : userComp) {
				if (complete.getCategory().equals("sell")) {
					totalSell += complete.getGoodsPrice();
				} else {
					totalBuy += complete.getGoodsPrice();
				}
			}
			mv.addObject("totalSell", totalSell);
			mv.addObject("totalBuy", totalBuy);
		}

		mv.setViewName("mypage/main");
		return mv;
	}

// 마이페이지 - 내 동네 설정	

	@GetMapping("mypage/location")
	public String indexLocation(Authentication auth) {
		return "mypage/location";
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
		Role mbRole = parseMbId.getRoleMbId(mbId);

		// 보호자의 경우
		if (mbRole == Role.GUARD) {
			List<MypageConnectionDTO> userChilds = mypageService.getMyChildrenList(mbId); // 연동된 아이 리스트

			// 맵으로 구현
			Map<String, List<MypageDTO>> allWish = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageDTO>> allTrade = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageDTO>> allComp = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageChatroomDTO>> allChat = new HashMap<String, List<MypageChatroomDTO>>();

			for (MypageConnectionDTO child : userChilds) {
				// 아이별 관심/구매 목록
				List<MypageDTO> userWish = mypageService.getWishList(child.getMbId());
				allWish.put(child.getMbId(), userWish);

				// 아이별 판매 목록
				List<MypageDTO> userTrade = mypageService.getTradeList(child.getMbId());
				allTrade.put(child.getMbId(), userTrade);

				// 아이별 거래완료 목록
				List<MypageDTO> userComp = mypageService.getCompleteList(child.getMbId());
				allComp.put(child.getMbId(), userComp);

				// 아이별 채팅 목록
				List<MypageChatroomDTO> userChat = mypageService.getChatroomList(child.getMbId());
				userChat.removeIf((dto) -> (dto.getMessage() == null)); // 주고 받은 메세지(마지막 메세지)가 없으면 목록에서 제외
				allChat.put(child.getMbId(), userChat);
			}

			mv.addObject("userChilds", userChilds);
			mv.addObject("allWish", allWish);
			mv.addObject("allTrade", allTrade);
			mv.addObject("allComp", allComp);
			mv.addObject("allChat", allChat);
		}
		// 아이의 경우
		else if (mbRole == Role.CHILD) {
			// 관심/구매 목록
			List<MypageDTO> userWish = mypageService.getWishList(mbId);

			// 판매 목록
			List<MypageDTO> userTrade = mypageService.getTradeList(mbId);

			// 거래완료 목록
			List<MypageDTO> userComp = mypageService.getCompleteList(mbId);

			// 채팅 목록
			List<MypageChatroomDTO> userChat = mypageService.getChatroomList(mbId);
			userChat.removeIf((dto) -> (dto.getMessage() == null)); // 주고 받은 메세지(마지막 메세지)가 없으면 목록에서 제외

			mv.addObject("userWish", userWish);
			mv.addObject("userTrade", userTrade);
			mv.addObject("userComp", userComp);
			mv.addObject("userChat", userChat);
		}

		mv.setViewName("mypage/mylist");
		return mv;
	}

	@GetMapping("mypage/block")
	public ModelAndView indexBlocklist(Authentication auth) {
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		// 보호자일 경우
		if (memberDTO.getMbRole() == Role.GUARD) {
			// 보호자의 차단목록
			List<MypageBlockDTO> guardBlock = mypageService.getBlockList(mbId);

			// 아이별 차단목록
			List<MypageConnectionDTO> userChilds = mypageService.getMyChildrenList(mbId);
			Map<String, List<MypageBlockDTO>> allBlock = new HashMap<String, List<MypageBlockDTO>>();
			for (MypageConnectionDTO child : userChilds) {
				List<MypageBlockDTO> userBlock = mypageService.getBlockList(child.getMbId());
				allBlock.put(child.getMbId(), userBlock);
			}

			mv.addObject("guardBlock", guardBlock);
			mv.addObject("userChilds", userChilds);
			mv.addObject("allBlock", allBlock);
		}
		// 아이일 경우
		else if (memberDTO.getMbRole() == Role.CHILD) {
			// 보호자의 차단목록
			MypageConnectionDTO userGuard = mypageService.getMyGuard(mbId);
			List<MypageBlockDTO> guardBlock = mypageService.getBlockList(userGuard.getMbId());

			// 아이의 차단목록
			List<MypageBlockDTO> userBlock = mypageService.getBlockList(mbId);

			mv.addObject("userBlock", userBlock);
			mv.addObject("userGuard", userGuard);
			mv.addObject("guardBlock", guardBlock);
		}

		mv.setViewName("mypage/blocklist");
		return mv;
	}

}
