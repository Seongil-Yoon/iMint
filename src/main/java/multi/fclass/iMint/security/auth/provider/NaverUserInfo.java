package multi.fclass.iMint.security.auth.provider;

import java.util.Map;

/**
 * @author Jungmin, Yang
 *
 */

public class NaverUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes;
	
	// attributes 에 {id=.., email=.., name=..} 이 넘어온다.
    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    // Provider(구글, 네이버, 카카오 중 1)
	@Override
	public String getMbProvider() {
		return "naver";
	}

	@Override
	public String getMbNick() {
		return (String) attributes.get("name");
	}

	@Override
	public String getMbEmail() {
		return (String) attributes.get("email");
	}

	@Override
	public String getMbId() {
		return (String) attributes.get("id");
	}

	public String getAgeRange() {
		String result = (String) attributes.get("age");
		System.out.println("연령대: " + result);
		return result;

	}

}