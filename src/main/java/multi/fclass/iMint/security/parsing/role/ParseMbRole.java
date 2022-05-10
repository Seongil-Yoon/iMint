package multi.fclass.iMint.security.parsing.role;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dao.ISecurityDAO;

/**
 * @author Junming, Yang
 *
 */

//모듈화
@Component
public class ParseMbRole {

	MemberDTO user;
	
	Role role;
	
	@Autowired
	ISecurityDAO securityDAO;
	
	public String parseMbRole(Authentication auth) {
		
		DefaultOAuth2User authorization = (DefaultOAuth2User) auth.getPrincipal();

		Collection<? extends GrantedAuthority> role = authorization.getAuthorities();
		String strRole = role.toString();
		
        return strRole;
	}

}
