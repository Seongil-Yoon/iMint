package multi.fclass.iMint.security.service;

import java.util.Collections;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import multi.fclass.iMint.member.dto.SessionMember;
import multi.fclass.iMint.mail.dto.MailDTO;
import multi.fclass.iMint.mail.service.IMailService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.auth.config.OAuthAttributes;
import multi.fclass.iMint.security.auth.provider.OAuth2UserInfo;
import multi.fclass.iMint.security.dao.ISecurityDAO;

/**
 * @author Jungmin, Yang
 *
 */

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService implements OAuth2UserService<OAuth2UserRequest, OAuth2User> {
    private final ISecurityDAO securityDAO;
    private final HttpSession httpSession;
    
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        
    	OAuth2UserService<OAuth2UserRequest, OAuth2User> delegate = new DefaultOAuth2UserService();
        OAuth2User oAuth2User = delegate.loadUser(userRequest);
        
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code 리턴(OAuth-Client라이브러리) -> Access Token 요청
		System.out.println("getAttributes: " + oAuth2User.getAttributes()); // 사용자 정보

		// code를 통해 구성한 정보
		System.out.println("userRequest clientRegistration : " + userRequest.getClientRegistration());
		// token을 통해 응답받은 회원정보
		System.out.println("oAuth2User : " + oAuth2User);
        
        // 현재 로그인 진행 중인 서비스를 구분하는 코드 // google 
        String mbProvider = userRequest
                .getClientRegistration()
                .getRegistrationId();
        // oauth2 로그인 진행 시 키가 되는 필드값 (고객 식별자 필드값) 
        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails()
                .getUserInfoEndpoint()
                .getUserNameAttributeName();
        
        String mbId = mbProvider + "_" + userNameAttributeName;
        
        System.out.println("======");
        System.out.println(mbProvider);
        System.out.println(userNameAttributeName);
        System.out.println("======");
        
        // OAuthAttributes: attribute를 담을 클래스 (개발자가 생성)
        OAuthAttributes attributes = OAuthAttributes
                .of(mbProvider, userNameAttributeName, oAuth2User.getAttributes()); // 로그인, 로그인한 유저 정보 받아오기
        
        MemberDTO memberDTO = SaveOrUpdate(attributes);
        
        // SessioUser: 세션에 사용자 정보를 저장하기 위한 DTO 클래스 (개발자가 생성)
        httpSession.setAttribute("memberDTO", new SessionMember(memberDTO));
        return new DefaultOAuth2User(
                Collections.singleton(new SimpleGrantedAuthority(memberDTO.getRoleKey())),
                attributes.getAttributes(),
                attributes.getNameAttributeKey()
        );
    }
    
    // 유저가 최초 로그인인지, 기존 유저인지 확인
    private MemberDTO SaveOrUpdate(OAuthAttributes attributes) {
    	
        MemberDTO memberDTO;
        
        if(securityDAO.findByMbId(attributes.getMbId()) != null){
            System.out.println("이미 가입되어 있는 회원입니다.");
            memberDTO = securityDAO.findByMbId(attributes.getMbId());
        }
        
        // 최초 로그인한 유저면 자동 가입 실행
        else {
        	memberDTO = attributes.toEntity();
        	memberDTO.setMbNick(memberDTO.getMbNick() +  + (int)(Math.random() * 10000 + 1)); // 닉네임에 랜덤값 추가
            securityDAO.savesns(memberDTO);
            
            System.out.println("최초 로그인으로 자동 가입됩니다.");
            memberDTO = securityDAO.findByMbId(attributes.getMbId());
        }

        return memberDTO;
    }


}