package multi.fclass.iMint.common.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Component
@ControllerAdvice
public class ExceptionAdvice {
	
	//예외처리 정책
	//로그를 남기고, 사용자에게 데이터베이스에서 문제가 생겼음을 안내
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(DataAccessException.class)
	public String dataAccessExceptionProcess(DataAccessException e, Model model) {
		e.printStackTrace();
		model.addAttribute("msg","데이터베이스 접근 중 예외가 발생하였습니다.");
		model.addAttribute("url","/");
		return "common/result";
	}


}
