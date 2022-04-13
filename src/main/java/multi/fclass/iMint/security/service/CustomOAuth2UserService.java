package multi.fclass.iMint.security.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import multi.fclass.iMint.security.auth.config.OAuthAttributes;
import multi.fclass.iMint.security.dao.IUserDAO;
import multi.fclass.iMint.security.model.Role;
import multi.fclass.iMint.security.model.SessionUser;
import multi.fclass.iMint.security.model.User;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final IUserDAO userDAO;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code 리턴(OAuth-Client라이브러리) -> Access Token 요청
		// userRequest(Access Token등 정보를 포함한) 정보 -> 구글로부터 회원 프로필 받아야 할 때 loadUser메서드 사용 
		System.out.println("getAttributes: " + oAuth2User.getAttributes()); // 사용자 정보

		// code를 통해 구성한 정보
		System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
		// token을 통해 응답받은 회원정보
		System.out.println("oAuth2User : " + oAuth2User);
        
        // 현재 로그인 진행 중인 서비스를 구분하는 코드 // google 
        String mbProvider = userRequest
                .getClientRegistration()
                .getRegistrationId();
        // oauth2 로그인 진행 시 키가 되는 필드값 (아마 고객 식별자) 
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        
        String mbId = mbProvider + "_" + userNameAttributeName;
        
        System.out.println("======");
        System.out.println(mbProvider);
        System.out.println(userNameAttributeName);
        System.out.println("======");
        
//        User userEntity = IUserDAO.findByMbId(mbId);
//        
//        userEntity = User.builder()
//				 .mbNo(null)
//				 .mbId(mbId)
//				 .mbProvider(mbProvider)
//				 .mbGuard(null)
//				 .mbNick(null)
//				 .mbEmail(null)
//				 .mbJoinDate(null)
//				 .mbInterest(null)
//				 .mbLocation(null)
//				 .mbRatingsTotal(null)
//				 .mbPin(null)
//				 .mbThumbnail(null)
//				 .mbIsdelete(null)
//				 .mbRole(Role.GAURD)
//				.build();
//        User userEntity = IUserDAO.findByMbId(mbId);
        
//		return new PrincipalDetails(userEntity, oAuth2User.getAttributes()); // Authentication 객체 안에 들어온다 

        
        // OAuthAttributes: attribute를 담을 클래스 (개발자가 생성)
        OAuthAttributes attributes = OAuthAttributes
                .of(mbProvider, userNameAttributeName, oAuth2User.getAttributes());
        User user = saveOrUpdate(attributes);
        // SessioUser: 세션에 사용자 정보를 저장하기 위한 DTO 클래스 (개발자가 생성)
        httpSession.setAttribute("user", new SessionUser(user));
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }
    // 유저가 있는지 확인(email로) 
    private User saveOrUpdate(OAuthAttributes attributes) {
        User user;
        if(userDAO.findByMbEmail(attributes.getMbEmail())!=null){
            System.out.println("이미 가입되어 있는 회원입니다.");
        	user=userDAO.findByMbEmail(attributes.getMbEmail());
        }
        else {
            user=attributes.toEntity();
            userDAO.save(user);
            System.out.println("최초 로그인으로 자동 가입됩니다.");
            user=userDAO.findByMbEmail(attributes.getMbEmail());
        }

        return user;
    }
}