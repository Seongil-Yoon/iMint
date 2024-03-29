package multi.fclass.iMint.main.controller;

import java.util.ArrayList;
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

import multi.fclass.iMint.ai.service.sttServiceImpl;
import multi.fclass.iMint.block.service.IBlockService;
import multi.fclass.iMint.main.service.MainServiceImpl;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Seongil, Yoon 메인페이지와 관련된 로직
 */
@Controller
public class MainController {

	@Autowired
	MainServiceImpl mainService;
	
	@Autowired
	IBlockService blockService;

	@Autowired
	ParseMbId parseService;

	@GetMapping("main")
	public String mainPage(Authentication auth, @RequestParam(defaultValue = "all") String goodsCategory,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "keyword", required = false) String keyword, Model model) {
		if (auth != null) {
			String mbId = parseService.parseMbId(auth);
			MemberDTO memberDTO = parseService.getMemberMbId(mbId);
			model.addAttribute("mbRole", memberDTO.getMbRole());
			model.addAttribute("userLocation", memberDTO.getMbLocation());
			model.addAttribute("goodsCategory", goodsCategory);
			model.addAttribute("searchOption", searchOption);
			model.addAttribute("keyword", keyword);
		}
		model.addAttribute("goodsCategory", goodsCategory);
		model.addAttribute("searchOption", searchOption);
		model.addAttribute("keyword", keyword);
		return "main";
	}

	@ResponseBody
	@GetMapping("goods-list/{goodsCategory}/{lastBoard}")
	public List<HashMap<String, Object>> goodsListMap(Authentication auth,
			@PathVariable(required = false) String goodsCategory, @PathVariable int lastBoard,
			@RequestParam("userLocation") String userLocation,
			@RequestParam(value = "searchOption", required = false) String searchOption,
			@RequestParam(value = "keyword", required = false) String keyword) {
		MemberDTO memberDTO = null;
		List<String> blocklist = null;
		if (auth == null) {
			blocklist = new ArrayList<String>();
			blocklist.add("");
			return mainService.goodsListMap(goodsCategory, lastBoard, userLocation, searchOption, keyword, blocklist);
		}
		String mbId = parseService.parseMbId(auth);
		memberDTO = parseService.getMemberMbId(mbId);
		
		blocklist = blockService.blocklist(mbId);
		
		
		return mainService.goodsListMap(goodsCategory, lastBoard, memberDTO.getMbLocation(), searchOption, keyword, blocklist);
	}

	

}
