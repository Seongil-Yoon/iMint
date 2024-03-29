package multi.fclass.iMint.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import multi.fclass.iMint.member.dto.Role;
import multi.fclass.iMint.common.code.ErrorCode;
import multi.fclass.iMint.common.exception.hadler.UnauthorizedException;
import multi.fclass.iMint.mail.dto.MailDTO;
import multi.fclass.iMint.mail.service.IMailService;
import multi.fclass.iMint.member.dto.MemberDTO;
import multi.fclass.iMint.security.GenerateCertCharacter;
import multi.fclass.iMint.security.dao.ISecurityDAO;
import multi.fclass.iMint.security.parsing.mbid.ParseMbId;
import multi.fclass.iMint.security.parsing.role.ParseMbRole;

/**
 * @author Jungmin, Yang
 *
 */

@Slf4j
@Controller 
public class IndexController {
	
	@Autowired
	private ISecurityDAO securityDAO;
	
	@Autowired
	ParseMbRole parseMbRole;
	
	@Autowired
	ParseMbId parseMbId;
	
    @SuppressWarnings("unused")
	@Autowired
    private AuthenticationManager authenticationManager; // 세션값 변경목적
    
    @SuppressWarnings("unused")
    private HttpSession httpSession; 
    
    @Autowired
	IMailService mailService;
    
    @Value("${root}")
	String root;
    
	@Value("${directory}")
	String directory;
	
    // 로그인 
	@GetMapping({"", "/"})
	public String loginForm(Authentication auth, Model model) { // 첫화면에서 로그인 여부, 회원 권한이 인증여부에 따라 다른 화면으로 전달 
		
		try { // 로그인 상태인 경우 
			String mbId = parseMbId.parseMbId(auth);
			MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
			model.addAttribute("memberDTO", memberDTO);

			// 회원가입 미완료(아이, 보호자 각각, 자진 탈퇴 회원)상태이면 회원가입 페이지로 이동 
			if(memberDTO.getMbRole() == Role.UN_CHILD || memberDTO.getMbRole() == Role.UN_GUARD) {
				return "member/register";							
			}
			// 관리자면 관리자 페이지로 이동 
			else if(memberDTO.getMbRole() == Role.ADMIN) {
				model.addAttribute(memberDTO);
				return "redirect:/admin/member";
			}
			// 관리자가 강퇴시킨 회원이면 index페이지로 이동 
			else if (memberDTO.getMbRole() == Role.GUEST) {
				return "index";							
			}
			// 권한이 가입완료된 회원(CHILD, GUARD)이면 메인으로 이동
			else {
				return "redirect:/main";	
			}
		} catch (Exception e) { // 비로그인 상태이면 로그인 페이지로 이동
			return "index";			
		}
	}
	
	// 가입 완료시키기
	@RequestMapping(value = "/err/denied-page")
	public String accessDenied(Authentication auth, HttpServletRequest req){
		
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		AccessDeniedException ade = (AccessDeniedException) req.getAttribute(WebAttributes.ACCESS_DENIED_403);
        log.info("---------- err/denied-page ---------");
		log.info("memberDTO : {}", memberDTO);
		log.info("exception : {}", ade); // 로그 기록
		
		return "err/403";
	}

	// 회원가입은 총 4단계 
	// 회원가입 2(보호자, 아이 모두): sns 가입(회원가입 1)후 register 페이지로 이동
	@GetMapping("/register/2")
	public String registersns(Authentication auth, Model model) { 
		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		// 모듈화 결과(아래 2줄)
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		
		log.info("---------- register ---------");
		log.info("memberDTO : {}", memberDTO);
		
		model.addAttribute(memberDTO);
		return "member/register";
	}
	
	// 회원가입 2에서 닉네임 중복 확인(비동기)
	@ResponseBody
	@RequestMapping("/register/nickname")
	public Map<String, String> nickname(String nickcheck, String mbId, Authentication auth) { 

		Map<String, String> map = new HashMap<String, String>();
		
		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		if (nickcheck.trim().isEmpty()){ // 입력된 문자가 없으면(입력된 값 전체가 공백이면)
			map.put("result", "blank");		
		}
		else if (securityDAO.findByMbNick(nickcheck) == null || securityDAO.findByMbNick(nickcheck).getMbId().equals(mbId) ) { // 입력된 닉네임을 사용중인 회원이 없거나, 본인이면
			map.put("result", "ok");
			map.put("nickcheck", nickcheck);
		}
		else{
			map.put("result", "duplicated");			
		}
		return map;		
	}
	
	// 회원가입 3(보호자, 아이 모두. 로직은 분리)
	@PostMapping("/register/3") 
	public String registersns(HttpServletRequest req, Authentication auth, Model model, String mbId, String mbRole, String mbNick, String mbEmail, String mbInterest) { // Authentication auth -> mbId로 연결하기 & 수정 & 권한 업데이트

		// 비 로그인
		if (auth == null) {
					throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}
		
		// 유저 정보 업데이트 
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);
		memberDTO.setMbNick(mbNick);
		memberDTO.setMbEmail(mbEmail);
		memberDTO.setMbInterest(mbInterest);
		
		securityDAO.updateregister3(memberDTO);

		model.addAttribute(memberDTO);
		
		if(mbRole.equals("UN_GUARD")) { // 미인증 보호자이면 
			return "mypage/location";
		}
		else { // 미인증 아이이면 
			return "member/register_connect";			
		}
	}
	

	// 회원가입 4(최종 회원가입 단계. 보호자, 아이 모두)
	// 부모-> 위치 설정 , 아이 -> 보호자 연동이 각각 끝나면 Role를 인증으로 변경
	// 회원가입 완료되면 다시 로그인 요청
	@PostMapping({ "", "/" })
	public String registerdetails(HttpServletRequest req, Authentication auth, Model model, String mbLocationOrGuard,
			String guardPin) throws IOException {

		// 비 로그인
		if (auth == null) {
			throw new UnauthorizedException(ErrorCode.UNAUTHORIZED);
		}

		// 현재 로그인한 회원의 정보 파싱
		String mbId = parseMbId.parseMbId(auth);
		MemberDTO memberDTO = parseMbId.getMemberMbId(mbId);

		model.addAttribute(memberDTO);
		
		try {
			// 미인증 보호자(UN_GUARD)인 경우 mbLocation 받아오기, 회원가입 마치면 GUARD로 권한 업그레이드
			if (memberDTO.getMbRole() == Role.UN_GUARD) { // 미인증 보호자
				memberDTO.setMbGuard(null);
				memberDTO.setMbLocation(mbLocationOrGuard);
				memberDTO.setMbRole(Role.GUARD);
				memberDTO.setMbPin(new GenerateCertCharacter().excuteGenerate());

				// DB저장
				securityDAO.updateregister4(memberDTO);

				// 세션 수정
				List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
				authorities.add(new SimpleGrantedAuthority(memberDTO.getRoleKey()));
				// 세션에 변경사항 저장
				SecurityContext context = SecurityContextHolder.getContext();
				// UsernamePasswordAuthenticationToken
				context.setAuthentication(
						new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, authorities));
				HttpSession session = req.getSession(true);
				// 위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
				session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
				model.addAttribute("register", memberDTO.getMbRole());
				
				// 메일발송
				HashMap<String, String> mailString = new HashMap<String, String>();
				mailString.put("msg", memberDTO.getMbNick() + "님의 아이와 연동하시려면 아래의 PIN 번호를 아이가 회원 가입할 때 알려주세요");
				mailString.put("pin", memberDTO.getMbPin());
				String htmlContent = "<p>" + mailString.get("msg") + "</p> <br> <h1>"
						+ mailString.get("pin") + "</h1> <br><br> <img src='cid:sample-img'>";
				MailDTO mailDto = MailDTO.builder()
						.address(memberDTO.getMbEmail())
						.title("[iMint]" + memberDTO.getMbNick() + "님 가입해주셔서 감사합니다.")
						.build();
				mailService.fileMailSend(mailDto, htmlContent);
				// end of 메일발송
				return "index";

			} else if (memberDTO.getMbRole() == Role.UN_CHILD) { // 미인증 아이
				MemberDTO guardMember = securityDAO.findByMbNick(mbLocationOrGuard);
				try {
					if (guardMember != null & guardMember.getMbPin().equals(guardPin)) {
						memberDTO.setMbGuard(guardMember.getMbId());
						memberDTO.setMbLocation(guardMember.getMbLocation());
						memberDTO.setMbRole(Role.CHILD);
						memberDTO.setMbPin(null);

						// DB저장
						securityDAO.updateregister4(memberDTO);

						// 세션 수정
						List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
						authorities.add(new SimpleGrantedAuthority(memberDTO.getRoleKey()));
						// 세션에 변경사항 저장
						SecurityContext context = SecurityContextHolder.getContext();
						// UsernamePasswordAuthenticationToken
						context.setAuthentication(
								new UsernamePasswordAuthenticationToken(memberDTO.getMbId(), null, authorities));
						HttpSession session = req.getSession(true);
						// 위에서 설정한 값을 Spring security에서 사용할 수 있도록 세션에 설정
						session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, context);
						
						model.addAttribute("register", memberDTO.getMbRole());
						return "index";
					} else { // 보호자의 입력정보가 틀리면 보호자 연동 페이지로 다시 보내기
						return "member/register_connect";
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					model.addAttribute("err", "정확한 보호자의 닉네임, Pin을 입력해주세요.");
					return "member/register_connect"; // 보호자의 입력정보가 틀리면 다시 보내기
				}
			} else if (memberDTO.getMbRole() == Role.GUARD) { // 가입 완료된 보호자
				return "redirect:/mypage/location";
			} else {
				return "index";	
			}

		} catch (ClassCastException e) {
			return "redirect:/mypage/location";
		} // catch end
		
	}//end of @PostMapping({ "", "/" })
}