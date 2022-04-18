package multi.fclass.iMint.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;

/**
 * @author Junming, Yang
 *
 */

@Mapper
@Repository
public interface IUserDAO {
	
    // 회원 조회 
    public User findByMbId(String mbId);
    public User findByMbEmail(String mbEmail);
    public User findByMbNick(String mbNick);

    // 회원가입 1차: sns 가입
    public void savesns(User user); 
    
    // 회원가입 3, 4차 
    public void updateregister3(User user);
    public void updateregister4(User user);

    // 4차 회원가입(최종)
    public void updatedetails(String mbId, String mbNick, Role mbRole, String mbEmail, String mbInterest, String mbLocation, String mbGuard); 
    
}