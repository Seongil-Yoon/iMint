package multi.fclass.iMint.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.member.dto.MemberDTO;

/**
 * @author Junming, Yang
 *
 */

@Mapper
public interface IMemberDAO {

    // 회원정보 수정
	public void updatemember(String mbId, String mbThumbnail, String mbNickname, String mbInterest);
	
	// 탈퇴
	public void updatedelete(String mbId);

	// 프로필 사진
	public void updatethumbnail(String mbId, String allname);

	// 동네
	public void updatelocation(MemberDTO memberDTO);
	
}
