package multi.fclass.iMint.main.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.goods.service.GoodsServiceImpl;
import multi.fclass.iMint.main.service.MainServiceImpl;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Seongil, Yoon 메인페이지와 관련된 로직
 */
@Controller
public class MainCotroller {

	@Autowired
	MainServiceImpl mainService;

	@Autowired
	ParseMbId parseService;

	@GetMapping("main")
	public String mainPage(Authentication auth, Model model) {
		if (auth != null) {
			String mbId = parseService.parseMbId(auth);
			MemberDTO memberDTO = parseService.getMemberMbId(mbId);
			model.addAttribute("userLocation", memberDTO.getMbLocation());
		}
		return "main";
	}

	@ResponseBody
	@GetMapping("goods-list/{lastBoard}")
	public List<HashMap<String, Object>> goodsListMap(Authentication auth, @PathVariable int lastBoard,
			@RequestParam("userLocation") String userLocation) {
		MemberDTO memberDTO = null;
		if (auth == null) {
			System.out.println("auth객체가 null");
			return mainService.goodsListMap(lastBoard, userLocation);
		}
		String mbId = parseService.parseMbId(auth);
		memberDTO = parseService.getMemberMbId(mbId);
		return mainService.goodsListMap(lastBoard, memberDTO.getMbLocation());
	}

}
