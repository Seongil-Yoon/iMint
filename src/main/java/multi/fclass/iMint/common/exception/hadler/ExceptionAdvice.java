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

import multi.fclass.iMint.common.exception.HandlableException;

//import multi.fclass.iMint.common.exception.HandlableException;

/**
 * @author Seongil, Yoon
 *
 */
@Component
@ControllerAdvice(basePackages = "multi.fclass.iMint")
public class ExceptionAdvice {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@ExceptionHandler(HandlableException.class)
	public String handlableExceptionProcess(HandlableException e, Model model) {
		if (e.error.STATUS == 401) {
			return UnauthorizedException(e, model);
		}
		if (e.error.STATUS == 403) {
			return ForbiddenException(e, model);
		}
		if (e.error.STATUS == 404) {
			return NotFoundException(e, model);
		}
		return InternalServerErrorException(e, model);

	}

	@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
	public String UnauthorizedException(HandlableException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "로그인먼저 해주세요🙏");
		model.addAttribute("path", "/register");
		return "err/401";
	}

	// 로그인은 했지만 타인의 게시물(자원)에 접근할 권한이 없음
	@ResponseStatus(code = HttpStatus.FORBIDDEN)
	public String ForbiddenException(HandlableException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "홈으로 돌아가기");
		model.addAttribute("path", "/main");
		return "err/403";
	}

	// 게시물(자원)이 존재하지 않음
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String NotFoundException(HandlableException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "홈으로 돌아가기");
		model.addAttribute("path", "/main");
		return "err/404";
	}

	// 서버 내부 오류
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public String InternalServerErrorException(HandlableException e, Model model) {
		model.addAttribute("timestamp", e.error.TIMESTAMP);
		model.addAttribute("status", e.error.STATUS);
		model.addAttribute("error", e.error.ERROR);
		model.addAttribute("message", "홈으로 돌아가기");
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
