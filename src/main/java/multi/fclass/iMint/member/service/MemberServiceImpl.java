package multi.fclass.iMint.member.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.common.service.IUtilService;
import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.member.dao.IMemberDAO;

@Service
public class MemberServiceImpl implements IMemberService {

	@Autowired
	IMemberDAO memberDAO;

	@Autowired
	IUtilService utilService;

	@Autowired
	IFileService fileService;

	@Autowired
	HttpSession httpSession;
		
	@Override
	public String selectMemberThumbnail(String mbId) {
		
		
		return null;
	}
	
	@Override
	public String insertMemberThumbnail(String mbId ,MultipartFile mbThumnail) {
		
		
		
		return null;
	}

	@Override
	public String deleteMemberThumbnail(String mbId) {
		
		
		
		return null;
	}

}
