package multi.fclass.iMint.security.auth.provider;

import java.util.Map;

/**
 * @author Jungmin, Yang
 *
 */

public class KakaoUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes;
	private Map<String, Object> attributesAccount;
	private Map<String, Object> attributesProfile;
	
	// attributes 에 {id=.., email=.., name=..} 이 넘어온다.
    public KakaoUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
        this.attributesAccount = (Map<String, Object>)attributes.get("kakao_account");
        this.attributesProfile = (Map<String, Object>)attributes.get("properties");
    }

    // Provider(구글, 네이버, 카카오 중 1)
	@Override
	public String getMbProvider() {
		return "kakao";
	}

	@Override
	public String getMbNick() {
		return (String) attributesProfile.get("nickname");
	}

	@Override
	public String getMbEmail() {
		return (String) attributesAccount.get("email");
	}

	@Override
	public String getMbId() {
		return String.valueOf(attributes.get("id"));
	}

	// 네이버와 동일한 형식으로 연령대 출력 변경
	@Override
	public String getAgeRange() {
		String result = (String) attributesAccount.get("age_range");
		result.replace("~", "-");
		System.out.println("연령대: " + result);
		return result;  
	}

}