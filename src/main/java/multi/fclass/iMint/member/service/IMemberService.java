package multi.fclass.iMint.member.service;

import org.springframework.web.multipart.MultipartFile;

public interface IMemberService {

	String selectMemberThumbnail(String mbId);

	String insertMemberThumbnail(String mbId, MultipartFile mbThumnail);

	String deleteMemberThumbnail(String mbId);

}
