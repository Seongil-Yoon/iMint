package multi.fclass.iMint.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.fclass.iMint.security.dto.Role;
import multi.fclass.iMint.security.dto.User;

@Mapper
@Repository
public interface IUserDAO {
    //로그인 & 회원가입
    public User findByMbId(String mbId);
    public User findByMbEmail(String mbEmail);
    public User findByMbNick(String mbNick);

    public void savesns(User user); // 1차 sns 가입
    // 회원가입 3, 4
//    public void updateregister3Child(User user);

    public void updateregister3(User user);
    public void updateregister4(User user);

    // 회원정보 수정(임시)
    public void updatedetails(String mbId, String mbNick, Role mbRole, String mbEmail, String mbInterest, String mbLocation, String mbGuard); // 4차 회원가입(최종)
    public void updateuser(User user);
    
}