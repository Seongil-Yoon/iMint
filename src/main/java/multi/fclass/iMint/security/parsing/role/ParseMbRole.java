package multi.fclass.iMint.security.parsing.role;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import multi.fclass.iMint.security.dao.IUserDAO;
import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;

//모듈화
@Component
public class ParseMbRole {

	User user;
	
	Role role;
	
	@Autowired
	IUserDAO userDAO;
	
	public String parseMbRole(Authentication auth) {
		
		DefaultOAuth2User authorization = (DefaultOAuth2User) auth.getPrincipal();
        System.out.println(authorization);

		Collection<? extends GrantedAuthority> role = authorization.getAuthorities();
		String strRole = role.toString();
	
		
        System.out.println("mbRole: " + strRole);
        return strRole;
	}

}
