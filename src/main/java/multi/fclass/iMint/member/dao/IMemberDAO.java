package multi.fclass.iMint.member.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Junming, Yang
 *
 */

@Mapper
public interface IMemberDAO {

    // 회원정보 수정
	public void updatemember(String mbId, String thumbnail, String nickname, String interest);
	
	// 탈퇴
	public void updatedelete(String mbId);

	// 프로필 사진
	public void updatethumbnail(String mbId, String allname);

}
