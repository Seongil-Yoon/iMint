package multi.fclass.iMint.security.auth.provider;

// OAuth2.0 제공자들 마다 응답해주는 속성값이 달라서 공통으로 만들어준다.
public interface OAuth2UserInfo {	
	String getMbId();
	String getMbProvider();
	String getMbNick();
	String getMbEmail();
	
	String getAgeRange(); // DB에 저장은 하지 않는다.
}