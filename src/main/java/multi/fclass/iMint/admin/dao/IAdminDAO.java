package multi.fclass.iMint.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import multi.fclass.iMint.admin.dto.AdminDTO;
import multi.fclass.iMint.member.dto.MemberDTO;

/**
 * @author Junming, Yang
 *
 */

@Mapper
public interface IAdminDAO {
	
	// 전체 회원 조회
	public List<MemberDTO> selectmemberall();
	
	// 동네별 회원 조회
	public List<AdminDTO> selectmemberstats();
	
	// 회원 강퇴
	public void banmember(String ban_member);
		
}
