package multi.fclass.iMint.block.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.block.dao.IBlockDAO;
import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

@Controller
public class BlockCotroller {
	
	@Autowired
	IBlockDAO blockDAO;

	@Autowired
	ParseMbId parseMbId;
	
	@Autowired
	IMemberDAO memberDAO;
	
	@Autowired
	ISecurityDAO securityDAO;
	
	// 채팅 혹은 상품 상세페이지에서 차단 요청시 결과 반환(비동기)
	@PostMapping("/block")
	@ResponseBody
	public HashMap<String, String>	block(String blockMbId, Authentication auth) {
		
		HashMap<String, String> map = new HashMap<>();
		
		// 본인
		String mbId = parseMbId.parseMbId(auth);
		blockDAO.block(mbId, blockMbId);
		
		// 비동기 응답 결과 전송
		map.put("mbId", mbId); // 차단한 본인		
		map.put("blockMbId", blockMbId); // 차단당한 상대방 
		map.put("result", "block");
		
		return map;
	}
	
	// 마이페이제에서 차단해제 요청시 반환(비동기)  
	@PostMapping("/unblock")
	@ResponseBody
	public HashMap<String, String>	unblock(String unblockMbId, Authentication auth) {
		
		HashMap<String, String> map = new HashMap<>();
		
		// 본인
		String mbId = parseMbId.parseMbId(auth);		
		blockDAO.block(mbId, unblockMbId);
		
		// 비동기 응답 결과 전송
		map.put("mbId", mbId); // 차단해제한 본인		
		map.put("unblockMbId", unblockMbId); // 차단해제당한 상대방 
		map.put("result", "unblock");
		
		return map;
	}
}
