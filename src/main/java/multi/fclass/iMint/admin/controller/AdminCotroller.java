package multi.fclass.iMint.admin.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.admin.dao.IAdminDAO;
import multi.fclass.iMint.admin.dto.AdminDTO;
import multi.fclass.iMint.admin.dto.ExcelUtil;
import multi.fclass.iMint.admin.service.AdminServiceImpl;
import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
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

		ModelAndView mv = new ModelAndView();
		
		try {
			String mbId = parseMbId.parseMbId(auth);
			MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
			
			if (memberDTO.getMbRole() == Role.ADMIN) {
				
				List<MemberDTO> memberlist = adminDAO.selectmemberall();
				
				mv.addObject("memberlist", memberlist);
				mv.setViewName("admin/admin_member");
			}
		} catch (Exception e) {
		}

		return mv;
	}
	
	// 회원 통계  
	@RequestMapping("/admin/stats/member")
	public ModelAndView stats(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		try {
			String mbId = parseMbId.parseMbId(auth);
			MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
			
			if (memberDTO.getMbRole() == Role.ADMIN) {
				
				List<AdminDTO> memberstats = adminDAO.selectmemberstats();
				
				mv.addObject("memberstats", memberstats);
				mv.setViewName("admin/stats_member");
			}
		} catch (Exception e) {
		}

		return mv;
	}
	
	// 회원 통계 excel
	@RequestMapping("/admin/stats/member/download")
	public ModelAndView statsdown(Authentication auth) throws FileNotFoundException, IOException {
		
		ModelAndView mv = new ModelAndView();

        List<Map<String, Object>> datas = new ArrayList<>();
		
		try {
			String mbId = parseMbId.parseMbId(auth);
			MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
			
			if (memberDTO.getMbRole() == Role.ADMIN) {
				
				List<AdminDTO> memberstats = adminDAO.selectmemberstats();
				
				for(AdminDTO adminDTO : memberstats) {
			        Map<String, Object> data = new HashMap<>();
			        data.put("동네", adminDTO.getMbLocation());
			        data.put("탈퇴회원", adminDTO.getMbWithdrawAll());
			        data.put("가입회원", adminDTO.getMbCntAll());
			        
			        datas.add(data);
				}
				
		        ExcelUtil excelUtil = new ExcelUtil();

		        String filepath = root + "/" + directory + "/" + "stats";
				List<String> path = new ArrayList<String>();
				path.add(root);
				path.add(directory);
				path.add("stats");
				
				// 폴더 생성 
				fileService.mkDir(path);
				
		        excelUtil.createExcelToFile(datas, filepath);
		        
				mv.addObject("memberstats", memberstats);
				mv.setViewName("admin/stats_member");
			}
		} catch (Exception e) {
		}

		return mv;
	}	
	// ajax로 변경요망..
//	@RequestMapping(value = "/productinsert", method = RequestMethod.GET)
//	public String insertproduct() {
//		return "manager_product_insert";
//	}
//	
//	@RequestMapping(value = "/productinsert", method = RequestMethod.POST, produces = {"application/json;charset=utf-8"})
//	public ModelAndView insertproduct(String name, String category, String color, int code, int price, int count) {
//		ModelAndView mv = new ModelAndView();
//		ProductDTO dto = new ProductDTO(name, category, color, code, price, count);
//		int insertRow = service.insertproduct(dto);
//		mv.addObject("insertRow", insertRow);
//		mv.setViewName("manager_product_result");
//		return mv;
//	}
//
//	@RequestMapping(value = "/productdelete", method = RequestMethod.GET)
//	public String deleteproduct() {
//		return "manager_product_delete"; 
////		
//	}
//
//	
//	@RequestMapping(value = "/productdelete", method = RequestMethod.POST)
//	public String deleteproduct(int code) {
////		// 삭제
//		int deletrow = service.deleteproduct(code);
//		if(deletrow == 1) { // 삭제되었으면 전체 회원가입 출력 
////			//	redirect:컨트롤러url매핑값 -> 해당 Url의 메서드 사용. 이렇게 해서 전체 조회 
//			return "redirect:/productlist"; 
//		}
//		return "redirect:/productinsert"; 
////		
//	}


}
