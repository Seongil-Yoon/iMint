package multi.fclass.iMint.admin.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.admin.dao.IAdminDAO;
import multi.fclass.iMint.admin.dto.AdminDTO;
import multi.fclass.iMint.admin.service.AdminServiceImpl;
import multi.fclass.iMint.common.code.ErrorCode;
import multi.fclass.iMint.common.exception.hadler.ForbiddenException;
import multi.fclass.iMint.common.exception.hadler.UnauthorizedException;
import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.goods.dto.GoodsDTO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 * @author Seongil, Yoon
 */

@Controller
public class AdminCotroller {

	@Autowired
	AdminServiceImpl service;

	@Autowired
	ParseMbId parseMbId;

	@Autowired
	IAdminDAO adminDAO;

	@Value("${root}")
	private String root;

	@Value("${directory}")
	private String directory;

	@Autowired
	IFileService fileService;

	// 전체 회원 조회
	@RequestMapping("/admin/member")
	public ModelAndView member(Authentication auth) {
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		ModelAndView mv = new ModelAndView();

		try {
			String mbId = parseMbId.parseMbId(auth);
			MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
			if (mbId.isEmpty() || !(memberDTO.getMbRole().equals(Role.ADMIN))) {
				throw new ForbiddenException(ErrorCode.FORBIDDEN);
			}

			List<MemberDTO> memberlist = adminDAO.selectmemberall();

			mv.addObject("memberlist", memberlist);
			mv.setViewName("admin/admin_member");
		} catch (Exception e) {
		}

		return mv;
	}

	// 관리자가 선택한 회원 강퇴(비동기)
	@ResponseBody
	@PostMapping("/admin/member/ban")
	public HashMap<String, String> ban(Authentication auth, String ban_members) {
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		if (mbId.isEmpty() || !(memberDTO.getMbRole().equals(Role.ADMIN))) {
			throw new ForbiddenException(ErrorCode.FORBIDDEN);
		}

		HashMap<String, String> map = new HashMap<>();

		service.ban(ban_members);

		map.put("result", "success");

		return map;
	}

	// 회원 통계 페이지 이동
	@GetMapping("/admin/stats/member")
	public ModelAndView statsView(Authentication auth) {
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		ModelAndView mv = new ModelAndView();

		try {
			String mbId = parseMbId.parseMbId(auth);
			MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
			if (mbId.isEmpty() || !(memberDTO.getMbRole().equals(Role.ADMIN))) {
				throw new ForbiddenException(ErrorCode.FORBIDDEN);
			}

			List<AdminDTO> memberstats = adminDAO.selectmemberstats();

			mv.addObject("memberstats", memberstats);
			mv.setViewName("admin/stats_member");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return mv;
	}

	// 회원 통계 페이지 결과 조회
	@PostMapping("/admin/stats/member")
	@ResponseBody
	public Map<String, List<String>> stats(Authentication auth) {
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		if (mbId.isEmpty() || !(memberDTO.getMbRole().equals(Role.ADMIN))) {
			throw new ForbiddenException(ErrorCode.FORBIDDEN);
		}
		Map<String, List<String>> map = new HashMap();

		List<String> location = new ArrayList();
		List<String> regist = new ArrayList();
		List<String> withdraw = new ArrayList();

		List<AdminDTO> memberstats = adminDAO.selectmemberstats();

		for (AdminDTO adminDTO : memberstats) {
			location.add(adminDTO.getMbLocation());
			regist.add(adminDTO.getMbCntAll());
			withdraw.add(adminDTO.getMbWithdrawAll());
		}

		map.put("location", location);
		map.put("regist", regist);
		map.put("withdraw", withdraw);

		return map;
	}

	/*
	 * @author Seongil, Yoon
	 */
	@GetMapping("/admin/goods")
	public String adminGoodsView(Authentication auth,
			@RequestParam(value = "category", required = false, defaultValue = "") String goodsCategory,
			@RequestParam(value = "keyword", required = false, defaultValue = "") String keyword,
			@RequestParam(value = "page", defaultValue = "1") int page, Model model) {
		
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		if (mbId.isEmpty() || !(memberDTO.getMbRole().equals(Role.ADMIN))) {
			throw new ForbiddenException(ErrorCode.FORBIDDEN);
		}
		
		int pageNums = service.goodsCount(keyword, goodsCategory);
		List<GoodsDTO> goodsList = service.adminGoodsList(keyword, goodsCategory, page);

		model.addAttribute("pageNums", pageNums);
		model.addAttribute("goodsList", goodsList);

		return "/admin/admin_goods";
	}

}
