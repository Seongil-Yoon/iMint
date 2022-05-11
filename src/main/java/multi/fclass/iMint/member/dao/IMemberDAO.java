package multi.fclass.iMint.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;

/**
 * @author Junming, Yang
 *
 */

@Mapper
public interface IMemberDAO {

    // 회원정보 수정
	public void updatemember(MemberDTO memberDTO);
	
	// 탈퇴
	public void updatedelete(String mbId, Role mbRole);

	// 동네
	public void updatelocation(MemberDTO memberDTO);
	
	// 프로필 사진
	public void updatethumbnail(MemberDTO memberDTO);

	// 프로필 사진 삭제
	public void updatedelthumbnail(String mbId);

}
