package multi.fclass.iMint.block.controller;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import multi.fclass.iMint.block.service.IBlockService;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

@Controller
public class BlockCotroller {

	@Autowired
	IBlockService service;

	@Autowired
	ParseMbId parseMbId;

	// 채팅 혹은 상품 상세페이지에서 차단 요청시 결과 반환(비동기)
	@PostMapping("/block")
	@ResponseBody
	public HashMap<String, String> block(String blockMbId, Authentication auth) {

		HashMap<String, String> map = new HashMap<>();

		// 본인
		String mbId = parseMbId.parseMbId(auth);

		service.block(mbId, blockMbId);

		// 비동기 응답 결과 전송
		map.put("mbId", mbId); // 차단한 본인
		map.put("blockMbId", blockMbId); // 차단당한 상대방
		map.put("result", "block");

		return map;
	}

	// 마이페이제에서 차단해제 요청시 반환(비동기)
	@PostMapping("/unblock")
	@ResponseBody
	public HashMap<String, String> unblock(String unblockMbId, Authentication auth) {

		HashMap<String, String> map = new HashMap<>();

		// 본인
		String mbId = parseMbId.parseMbId(auth);
		service.unblock(mbId, unblockMbId);

		// 비동기 응답 결과 전송
		map.put("mbId", mbId); // 차단해제한 본인
		map.put("unblockMbId", unblockMbId); // 차단해제당한 상대방
		map.put("result", "unblock");

		return map;
	}
}
