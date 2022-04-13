package multi.fclass.iMint.security.auth.provider;

import java.util.Map;

public class NaverUserInfo implements OAuth2UserInfo{

	private Map<String, Object> attributes;
	
	// attributes 에 {id=.., email=.., name=..} 이 넘어온다.
    public NaverUserInfo(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

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

}