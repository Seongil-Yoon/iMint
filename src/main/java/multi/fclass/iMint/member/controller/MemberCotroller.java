package multi.fclass.iMint.member.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import multi.fclass.iMint.common.service.IFileService;
import multi.fclass.iMint.member.dao.IMemberDAO;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.member.service.IMemberService;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;

/**
 * @author Junming, Yang
 *
 */

@Controller
public class MemberCotroller {
	
	@Autowired
	MemberDTO memberDTO;
	
	@Autowired
	IMemberDAO memberDAO;
	
	@Autowired
	ISecurityDAO securityDAO;
	
	@Autowired
	ParseMbId parseMbId;
	
	@Autowired
	IMemberService memberService;
	
	@Autowired
	IFileService fileService;
	
	@Value("${root}")
	private String root;

	@Value("${directory}")
	private String directory;
	
	@Value("${memberImagePath}")
	String memberImagePath;
	
	//URL 매핑 수정(회원가입 수정/탈퇴-> 보호자, 아이 구분 x)
	@GetMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		mv.addObject("memberDTO", memberDTO);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-edit");
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-edit");
		}
		
		return mv;
	}
	
	@PostMapping("/mypage/edit")
	public ModelAndView updateuser(Authentication auth, MultipartFile thumbnail, String nickname, String interest) throws IOException {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		String mbRole = memberDTO.getMbRole().toString();
		String provider = memberDTO.getMbProvider();
		
		// 전체 저장경로 + 파일 이름 
		// ex. ../GUARD/naver/naver_sdfklw242.jpg
		String mbThumbnail = null;
		
		// 파일 업로드
		try {		
		String savePath = root + "/" + directory + "/" + memberImagePath + "/" + mbRole + "/" + provider; // 저장경로: 1. guard / child 별로 지정 2.provider 별로 지정

		List<String> path = new ArrayList<String>();
		path.add(root);
		path.add(directory);
		path.add(memberImagePath);
		path.add(mbRole);
		path.add(provider);
		
		// 폴더 생성 
		fileService.mkDir(path);

		System.out.println("thumbnail" + thumbnail);
				
			if(!thumbnail.isEmpty()) {
				
				// 원래 파일 명에서 확장자(.) 추출 
				String ext = thumbnail.getOriginalFilename().substring(thumbnail.getOriginalFilename().indexOf("."));
	
				// 파일내용 + 파일명 --> 서버의 특정폴더(c:upload)에 영구저장. 서버가 종료되더라도 폴더에 저장.
				String newname = mbId + ext;
				mbThumbnail = savePath + "/" + newname;
				
				System.out.println("mbThumbnail: " + mbThumbnail);
				
				File serverfile = new File(mbThumbnail);
				thumbnail.transferTo(serverfile);
				
			} // if end
		}
		catch (Exception e) {
			e.printStackTrace();
		}		
		memberDTO.setMbThumbnail(mbThumbnail);			
		memberDTO.setMbNick(nickname);
		memberDTO.setMbInterest(interest);
		
		mv.addObject("memberDTO", memberDTO);
		
		memberDAO.updatemember(mbId, mbThumbnail, nickname, interest);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("redirect:/mypage");
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("redirect:/mypage");
		}
		
		return mv;
	}
	
	
	@GetMapping("/mypage/withdraw")
	public ModelAndView	deleteuser(Authentication auth) {
		
		ModelAndView mv = new ModelAndView();

		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		mv.addObject("memberDTO", memberDTO);
		
		if(memberDTO.getMbRole() == Role.GUARD) {
			mv.setViewName("member/guard-mypage/guard-withdraw"); 
		}
		else if(memberDTO.getMbRole() == Role.CHILD) {
			mv.setViewName("member/baby-mypage/baby-withdraw");
		}
		
		return mv;
	}
	
	@PostMapping("/mypage/withdraw")
	public String deleteuserresult(HttpServletRequest req, Authentication auth) {
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);


		
	//  cascade로 할 수 있는지? 우선은 리스트에 아이를 담아서 처리(테스트필요). 보호자가 탈퇴하면 자동으로 아이도 탈퇴처리 필요
		if (memberDTO.getMbRole() == Role.GUARD) { // 보호자일 때만 처리
//			List<MemberDTO> childlist = securityDAO.findByMbGuard(mbId);
//			for (int i = 0; i <= childlist.size(); i++) { // childlist.size()
//				MemberDTO childmember = childlist.get(i);
//				memberDAO.updatedelete(childmember.getMbId()); // 한 명씩 모두 탈퇴
//			};
			try {
				List<MemberDTO> childlist = securityDAO.findByMbGuard(mbId);
				System.out.println("childlist: "+childlist);
				for (MemberDTO childMemberDTO : childlist) { // childlist.size()
					System.out.println("childMemberDTO: "+childMemberDTO);
					String childMbId = childMemberDTO.getMbId();
					memberDAO.updatedelete(childMbId); // 한 명씩 모두 탈퇴
				};			
			} catch (Exception err) {
				err.printStackTrace();
			}
		}

		// 아이 모두 탈퇴시킨 뒤 보호자 탈퇴
		memberDAO.updatedelete(mbId); 
		
		// 세션에 변경사항 저장
		SecurityContext context = SecurityContextHolder.getContext();
		// UsernamePasswordAuthenticationToken
		context.setAuthentication(new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, null)); // Role 삭제
		HttpSession session = req.getSession(true);
		//위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
		session.setAttribute(HttpSessionSecurityContextRepository.
		                       SPRING_SECURITY_CONTEXT_KEY, context);
		
		// 탈퇴하면 로그인 페이지로 보내기			
		return "member/login";
	}
	
	// 프로필사진은 1개만 지정. 원래 파일명 저장 X. 삭제시 DB에서 삭제. 회원 탈퇴시 사진도 자동 삭제(사진이 컬럼이므로 따로 처리 필요 X)
	@ResponseBody
	@RequestMapping("/mypage/edit/thumbnail")
	public Map<String, String> upload(MultipartFile thumbnail, Authentication auth) throws IOException { // @ModelAttribute("뷰가 받을 이름"): 뷰로 전달해주고 싶을 때.

		Map<String, String> map = new HashMap<String, String>();

		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		String mbRole = memberDTO.getMbRole().toString();
		String provider = memberDTO.getMbProvider();
		
//		// ex. ../GUARD/naver/naver_sdfklw242.jpg
				
		String savePath = root + "/" + mbRole + "/" + provider; // 저장경로: 1. guard / child 별로 지정 2.provider 별로 지정

		List<String> path = new ArrayList<String>();
		path.add(mbRole);
		path.add(provider);
		
		// 폴더 생성 
		fileService.mkDir(path);

		// 파일 업로드
		
		if(!thumbnail.isEmpty()) {
			
			// 원래 파일 명에서 확장자(.) 추출 
			String ext = thumbnail.getOriginalFilename().substring(thumbnail.getOriginalFilename().indexOf("."));

			// 파일내용 + 파일명 --> 서버의 특정폴더(c:upload)에 영구저장. 서버가 종료되더라도 폴더에 저장.
			String newname = mbId + "." + ext;
			String mbThumbnail = savePath + "/" + newname;
			
			File serverfile = new File(newname);
			thumbnail.transferTo(serverfile);
			
			// db에 업데이트 하기(저장경로 + 파일 이름)
			memberDTO.setMbThumbnail(mbThumbnail);
			memberDAO.updatethumbnail(memberDTO);
			
			map.put("result", "success");
			
			return map;
		}
		else {
			map.put("result", "failure");
			return map;
		}
		
	}
	
}
