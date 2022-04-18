package multi.fclass.iMint.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.security.dao.IUserDAO;
import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

@Controller
public class MemberCotroller {
	
	@Autowired
	User user;
	
	@Autowired
	IUserDAO userDAO;
	
	@RequestMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		ParseMbId parseMbId = new ParseMbId();
		String mbId = parseMbId.parseMbId(auth);
		User user = parseMbId.getUserMbId(mbId);
		
		if(user.getMbRole() == Role.GUARD) {
			
		}
		
		
		mv.addObject(mv);
		mv.setViewName(null);
		
		return mv;
	}
	
}
