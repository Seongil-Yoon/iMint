package multi.fclass.iMint.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import multi.fclass.iMint.security.model.User;

@Mapper
@Repository
public interface IUserDAO {
    //로그인 & 회원가입
    public User findByMbId(String mbId);
    public User findByMbEmail(String mbEmail);
    public void save(User user); // 결과값 받아와기 
}