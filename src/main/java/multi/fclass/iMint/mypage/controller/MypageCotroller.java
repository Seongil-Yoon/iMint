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
import multi.fclass.iMint.mypage.dto.MypageChatroomDTO;
import multi.fclass.iMint.mypage.dto.MypageConnectionDTO;
import multi.fclass.iMint.mypage.dto.MypageDTO;
import multi.fclass.iMint.mypage.service.IMypageService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.security.parsing.role.ParseMbRole;

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
	ParseMbRole parseMbRole;

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
			List<MypageDTO> userWish = mypageService.getWishAndReserveList(mbId);
			mv.addObject("userWish", userWish.size());

			List<MypageDTO> userSell = mypageService.getSellingList(mbId);
			mv.addObject("userSell", userSell.size());

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
			Map<String, List<MypageDTO>> allSell = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageDTO>> allComp = new HashMap<String, List<MypageDTO>>();
			Map<String, List<MypageChatroomDTO>> allChat = new HashMap<String, List<MypageChatroomDTO>>();

			for (MypageConnectionDTO child : userChilds) {
				// 아이별 관심/구매 목록
				List<MypageDTO> userWish = mypageService.getWishAndReserveList(child.getMbId());
				allWish.put(child.getMbId(), userWish);

				// 아이별 판매 목록
				List<MypageDTO> userSell = mypageService.getSellingList(child.getMbId());
				allSell.put(child.getMbId(), userSell);

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
			mv.addObject("allSell", allSell);
			mv.addObject("allComp", allComp);
			mv.addObject("allChat", allChat);
		}
		// 아이의 경우
		else if (mbRole == Role.CHILD) {
			// 관심/구매 목록
			List<MypageDTO> userWish = mypageService.getWishAndReserveList(mbId);

			// 판매 목록
			List<MypageDTO> userSell = mypageService.getSellingList(mbId);

			// 거래완료 목록
			List<MypageDTO> userComp = mypageService.getCompleteList(mbId);

			// 채팅 목록
			List<MypageChatroomDTO> userChat = mypageService.getChatroomList(mbId);
			userChat.removeIf((dto) -> (dto.getMessage() == null)); // 주고 받은 메세지(마지막 메세지)가 없으면 목록에서 제외
			
			mv.addObject("userWish", userWish);
			mv.addObject("userSell", userSell);
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

		
		if(memberDTO.getMbRole() == Role.GUARD) {
			List<MypageConnectionDTO> userChilds = mypageService.getMyChildrenList(mbId);
			mv.addObject("userChilds", userChilds);
			mv.setViewName("mypage/blocklist"); 
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("mypage/blocklist");
		}
		
		return mv;
	}
	
}
