package multi.fclass.iMint.common.exception.hadler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author Seongil, Yoon
 *
 */
@Component
@ControllerAdvice(basePackages = "multi.fclass.iMint")
public class ExceptionAdvice extends ResponseEntityExceptionHandler {
	// ResponseEntity<>를 반환한다면 @ResponseStatus를 없애도 된다
	
	Logger logger = LoggerFactory.getLogger(this.getClass());

	// 로그인을 하지 않아서 인증된 사용자가 아님, 출입자체가 불가능
	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String UnauthorizedException(UnauthorizedException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "로그인먼저 해주세요🙏");
		model.addAttribute("path", "/");
		return "err/401";
	}

	// 로그인은 했지만 타인의 게시물(자원)에 접근할 권한이 없음
	@ExceptionHandler(ForbiddenException.class)
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String ForbiddenException(ForbiddenException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "접근 권한이 없습니다");
		model.addAttribute("path", "/main");
		return "err/403";
	}

	// 게시물(자원)이 존재하지 않음
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String NotFoundException(NotFoundException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "찾을수 없는 페이지 입니다.");
		model.addAttribute("path", "/main");
		return "err/404";
	}

	// 서버 내부 오류
	@ExceptionHandler(InternalServerErrorException.class)
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public String InternalServerErrorException(InternalServerErrorException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "대단히 죄송합니다🙏");
		model.addAttribute("path", "/main");
		return "err/500";
	}

	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceotionProcess(DataAccessException e, Model model) {
		e.printStackTrace();
		model.addAttribute("msg", "데이터베이스 접근 중에 예외가 발생하였습니다.");
		model.addAttribute("url", "/");
		return "err/500";
	}

}
