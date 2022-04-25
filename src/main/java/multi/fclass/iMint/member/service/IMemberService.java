package multi.fclass.iMint.member.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.member.dto.MemberDTO;

public interface IMemberService {

//	String selectMemberThumbnail(String mbId);
//
//	MemberDTO insertMemberThumbnail(String mbId, MultipartFile mbThumnail) throws IOException;
//
//	String deleteMemberThumbnail(String mbId);

	// new 
	MemberDTO updateuser(String mbId, MultipartFile thumbnail, String nickname, String interest) throws IOException;


}
