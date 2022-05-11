package multi.fclass.iMint.block.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

@Controller
public class BlockCotroller {

	@Autowired
	ParseMbId parseMbId;
	
	@Autowired
	IMemberDAO memberDAO;
	
	// 일단은 아이만 고려
	// p.28 에서 사용자 차단 클릭시 (baby-mylist.jsp)
	@RequestMapping("/mypage/block")
	public ModelAndView	block(String blockNick, Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		// 본인
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		// 차단한 사용자
		MemberDTO blockMember = parseMbId.getMemberMbId(blockNick);

		mv.addObject("memberDTO", memberDTO);
		mv.addObject("blockMember", blockMember);

		mv.setViewName("member/baby-mypage/baby-blocklist");
		
		return mv;
	}
	
	@PostMapping("/mypage/unblock")
	public ModelAndView	unblock(String unblockNick, Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		// 본인
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		// 차단해제된 사용자
		MemberDTO unblockMember = parseMbId.getMemberMbId(unblockNick);

		mv.addObject("memberDTO", memberDTO);
		mv.addObject("unblockMember", unblockMember);
		
		mv.setViewName("member/baby-mypage/baby-blocklist");
		
		return mv;
	}
}
