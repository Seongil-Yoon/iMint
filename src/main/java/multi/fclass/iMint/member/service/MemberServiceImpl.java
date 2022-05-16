package multi.fclass.iMint.member.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.common.service.IUtilService;
import multi.fclass.iMint.goods.dao.IGoodsDAO;
import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

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

	@Autowired
	ParseMbId parseMbId;

	@Value("${root}")
	private String root;

	@Value("${directory}")
	private String directory;

	@Value("${memberImagePath}")
	String memberImagePath;

	@Override
	public MemberDTO updateuser(String mbId, MultipartFile thumbnail, String nickname, String interest)
			throws IOException {

		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		String mbRole = memberDTO.getMbRole().toString();
		String provider = memberDTO.getMbProvider();

		// 전체 저장경로 + 파일 이름
		// ex. ../GUARD/naver/naver_sdfklw242.jpg
		String mbThumbnail = null;

		// 파일 업로드
		try {
			String savePath = "/" + directory + "/" + memberImagePath + "/" + mbRole + "/" + provider; // 저장경로: 1. guard
																										// / child 별로 지정
																										// 2.provider 별로
																										// 지정

			List<String> path = new ArrayList<String>();
			path.add(root);
			path.add(directory);
			path.add(memberImagePath);
			path.add(mbRole);
			path.add(provider);

			// 폴더 생성
			fileService.mkDir(path);

			if (!thumbnail.isEmpty()) {

				// 원래 파일 명에서 확장자(.) 추출
				String ext = thumbnail.getOriginalFilename().substring(thumbnail.getOriginalFilename().indexOf("."));

				// 파일내용 + 파일명 --> 서버의 특정폴더(c:upload)에 영구저장. 서버가 종료되더라도 폴더에 저장.
				String newname = mbId + ext;
				mbThumbnail = savePath + "/" + newname;

				memberDTO.setMbThumbnail(mbThumbnail);

				// 파일 업로드
				File serverfile = new File(root.concat(mbThumbnail));
				if (serverfile.exists()) {
					serverfile.canExecute();
					serverfile.canRead();
					serverfile.canWrite();
				}
				thumbnail.transferTo(serverfile);

			} // if end
			else { // 전달된 파일이 없으면
				if (memberDTO.getMbThumbnail() != null) { // 원래 파일있으면
					mbThumbnail = memberDTO.getMbThumbnail(); // 원래 파일 유지
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (!nickname.equals("")) {
			System.out.println("전달된 닉네임 있음");
			memberDTO.setMbNick(nickname);
		}

		if (!interest.equals("")) {
			System.out.println("전달된 관심사 있음");
			memberDTO.setMbInterest(interest);
		}

		memberDAO.updatemember(memberDTO);

		return memberDTO;
	}

}
