package multi.fclass.iMint.member.dao;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.dto.User;

@Mapper
public interface IMemberDAO {

    // 회원정보 수정
	public void updateuser(String mbId, String thumbnail, String nickname, String interest);
	
	// 탈퇴
	public void updatedelete(String mbId);
}
