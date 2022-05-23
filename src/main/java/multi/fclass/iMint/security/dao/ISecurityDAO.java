package multi.fclass.iMint.security.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.dto.MemberDTO;

/**
 * @author Jungmin, Yang
 *
 */

@Mapper
@Repository
public interface ISecurityDAO {
	
    // 회원 조회 
    public MemberDTO findByMbId(String mbId);
    public MemberDTO findByMbEmail(String mbEmail);
    public MemberDTO findByMbNick(String mbNick);
    public List<MemberDTO> findByMbGuard(String mbGuard); // 보호자의 mbId로 해당 보호자에 연동된 아이 계정 모두 조회 

    // 회원가입 1차: sns 가입
    public void savesns(MemberDTO memberDTO); 
    
    // 회원가입 3, 4차 
    public void updateregister3(MemberDTO memberDTO);
    public void updateregister4(MemberDTO memberDTO);

    // 4차 회원가입(최종)
    public void updatedetails(String mbId, String mbNick, Role mbRole, String mbEmail, String mbInterest, String mbLocation, String mbGuard); 
    
}