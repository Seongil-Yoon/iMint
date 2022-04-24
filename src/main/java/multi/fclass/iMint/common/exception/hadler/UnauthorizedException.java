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

import multi.fclass.iMint.common.code.ErrorCode;
import multi.fclass.iMint.common.exception.HandlableException;

/**
 * @author Seongil, Yoon
 *
 */
public class UnauthorizedException extends RuntimeException {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public ErrorCode error;

	// 로그인을 하지 않아서 인증된 사용자가 아님, 출입자체가 불가능
	public UnauthorizedException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]); // stackTrace를 비워준다.
	}

	public UnauthorizedException(ErrorCode error, Exception e) {
		this.error = error;
		e.printStackTrace();
		this.setStackTrace(new StackTraceElement[0]); // stackTrace를 비워준다.
	}

}
