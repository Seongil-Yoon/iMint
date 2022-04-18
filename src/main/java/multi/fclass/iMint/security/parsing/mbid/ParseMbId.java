package multi.fclass.iMint.security.parsing.mbid;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Component;

import multi.fclass.iMint.security.dao.IUserDAO;
import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;

// 모듈화
@Component
public class ParseMbId {

	User user;
	
	@Autowired
	IUserDAO userDAO;
	
	// mbId파싱
	public String parseMbId(Authentication auth) {
		DefaultOAuth2User authorization = (DefaultOAuth2User) auth.getPrincipal();

		String mbId = null;

		// 구글(아이 테스트용) 
		if(auth.getName() != null & auth.getName().length() <= 40) { // 네이버 (임시 숫자 크기 지정)
			mbId= "google_" + auth.getName();
		}
		else {
			// 카카오 
			String customerId = authorization.getName();
			mbId = "kakao_" + customerId;
			
			Map<String, Object> naverMap = null;
			if (customerId.length() >= 40) { // 네이버 (임시 숫자 크기 지정)
				naverMap = authorization.getAttributes();
				naverMap = (Map<String, Object>) naverMap.get("response");
				customerId = (String) naverMap.get("id");
				
				mbId = "naver_" + customerId;
			}
		}
		
		System.out.println("mbId: " + mbId);

		return mbId;
	}
	
	// mbId를 가진 유저 조회 
	public User getUserMbId(String mbId) {
		User user = userDAO.findByMbId(mbId);
        System.out.println("mbId가 " + mbId + "인 유저 조회 결과: " + user);
		return user;
	}
	
	// mbId유저의 Role 
	public Role getRoleMbId(String mbId) {
		User user = getUserMbId(mbId);
		Role mbRole = user.getMbRole();
        System.out.println("mbId가 " + mbId + "인 유저의 권한은 " + mbRole);
		return mbRole;
	}
	
}
