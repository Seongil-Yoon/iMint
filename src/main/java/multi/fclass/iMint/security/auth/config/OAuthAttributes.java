package multi.fclass.iMint.security.auth.config;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;

import lombok.Builder;
import lombok.Getter;
import multi.fclass.iMint.security.auth.provider.KakaoUserInfo;
import multi.fclass.iMint.security.auth.provider.NaverUserInfo;
import multi.fclass.iMint.security.auth.provider.OAuth2UserInfo;
import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;


@Getter
public class OAuthAttributes {

    private Map<String,Object> attributes;
    private String nameAttributeKey;
	// 등록 순서 
	private Integer mbNo;

	// 회원ID
	private String mbId;

	// 사이트SNS
	private String mbProvider;
	
	// 보호자
	private String mbGuard;

	// 닉네임
	private String mbNick;

	// 이메일
	private String mbEmail;

	// 가입일자
	private LocalDateTime mbJoinDate;

	// 관심사
	private String mbInterest;

	// 내 동네
	private String mbLocation;

	// 평가점수
	private Integer mbRatingsTotal;

	// 아이등록인증PIN
	private String mbPin;

	// 프로필사진
	private String mbThumbnail;

	// 탈퇴여부
	private Boolean mbIsdelete;
	
	// 권한
	private Role mbRole;
	
    @Builder
    public OAuthAttributes(Map<String, Object> attributes, String nameAttributeKey, String mbId, String mbProvider,
    		String mbGuard, String mbNick, String mbEmail, LocalDateTime mbJoinDate, Integer mbRatingsTotal, Boolean mbIsdelete,
    		Role mbRole) {
    	super();
    	this.attributes = attributes;
    	this.nameAttributeKey = nameAttributeKey;
    	this.mbId = mbId;
    	this.mbProvider = mbProvider;
    	this.mbGuard = mbGuard;
    	this.mbNick = mbNick;
    	this.mbEmail = mbEmail;
    	this.mbJoinDate = mbJoinDate;
    	this.mbRatingsTotal = mbRatingsTotal;
    	this.mbIsdelete = mbIsdelete;
    	this.mbRole = mbRole; // 나중에 나이에 따라서 수정 필요 
    }
    
    public static OAuthAttributes of(String mbProvider,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
    	if (mbProvider.equals("google")) {
    		return ofGoogle(userNameAttributeName, attributes);
		}
    	else if (mbProvider.equals("naver")) {
    		return ofNaver(userNameAttributeName, attributes);
		}
    	else if (mbProvider.equals("kakao")) {
    		return ofKakao(userNameAttributeName, attributes);
		}
    	else {
    		System.out.println("지원하지 않는 OAuth provider입니다.");
    		return null;
		}
    }
    
    // 아이 테스트용(구글은 연령대를 못 받아와서 원래는 미사용) 
    public static OAuthAttributes ofGoogle(String userNameAttributeName,
                                           Map<String, Object> attributes) {
    	System.out.println("구글 로그인 호출");
        return OAuthAttributes.builder()
            	.mbId ("google_"+(String) attributes.get("sub"))
            	.mbProvider ("goole") 
            	.mbGuard (null)
            	.mbNick ((String) attributes.get("name")) // 임시 
            	.mbEmail ((String) attributes.get("email")) 
            	.mbIsdelete (false) 
                .mbRole(Role.UN_CHILD) // 테스트용으로 구글이 아이라 가정 
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }
    
    public static OAuthAttributes ofNaver(String userNameAttributeName,
            Map<String, Object> attributes) {
    	
    	System.out.println("네이버 로그인 호출");
    	Role role = null;

		OAuth2UserInfo oAuth2UserInfo = new NaverUserInfo((Map<String, Object>)attributes.get("response"));
		System.out.println("response: " + oAuth2UserInfo.getMbEmail());

		if (oAuth2UserInfo.getAgeRange().equals("0-9") || oAuth2UserInfo.getAgeRange().equals("10-19") ) { // 나이가 20살 이하이면
			role = Role.UN_CHILD;			
		}
		
		else { // 나이가 20살 이상이면
				role = Role.UN_GUARD;
		}  
				
			// 연령대 받아온 경우
			return OAuthAttributes.builder()
	            	.mbId ("naver_"+(String) oAuth2UserInfo.getMbId())
	            	.mbProvider ("naver") 
	            	.mbGuard (null) // 임시  
	            	.mbNick ((String) oAuth2UserInfo.getMbNick()) // 임시 
	            	.mbEmail ((String) oAuth2UserInfo.getMbEmail()) 
	            	.mbIsdelete (false) 
	                .attributes(attributes)
	                .mbRole(role) 
	                .nameAttributeKey(userNameAttributeName)
	                .build();
    }

    
    public static OAuthAttributes ofKakao(String userNameAttributeName,
            Map<String, Object> attributes) {
    	System.out.println("카카오 로그인 호출");
    	Role role = null;
		OAuth2UserInfo oAuth2UserInfo = new KakaoUserInfo((Map<String, Object>)attributes);
		System.out.println("response: " + oAuth2UserInfo.getMbEmail());

		if (oAuth2UserInfo.getAgeRange().equals("0~9") || oAuth2UserInfo.getAgeRange().equals("10~19") ) { // 나이가 20살 이하이면
			role = Role.UN_CHILD;			
		}
		
		else { // 나이가 20살 이상이면
				role = Role.UN_GUARD;
		}  
				
			// 연령대 받아온 경우
			return OAuthAttributes.builder()
					.mbId ("kakao_"+(String) oAuth2UserInfo.getMbId())
					.mbProvider ("kakao") 
					.mbGuard (null) // 임시  
					.mbNick ((String) oAuth2UserInfo.getMbNick()) // 임시 
					.mbEmail ((String) oAuth2UserInfo.getMbEmail()) 
					.mbIsdelete (false) 
					.attributes(attributes)
					.mbRole(role) 
					.nameAttributeKey(userNameAttributeName)
					.build();
	}
    
    public User toEntity() {
        return User.builder()
        		.mbId (mbId)
        		.mbProvider (mbProvider)
        		.mbGuard (mbGuard)
        		.mbNick (mbNick)
        		.mbEmail (mbEmail)
                .mbLocation (mbLocation)
                .mbPin (mbPin)
                .mbRole(mbRole)
                .build();
    }

}
