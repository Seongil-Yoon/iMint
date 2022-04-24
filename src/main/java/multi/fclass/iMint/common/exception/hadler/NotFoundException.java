package multi.fclass.iMint.common.exception.hadler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import multi.fclass.iMint.common.code.ErrorCode;
import multi.fclass.iMint.common.exception.HandlableException;

/**
 * @author Seongil, Yoon
 *
 */
public class NotFoundException extends RuntimeException {

	Logger logger = LoggerFactory.getLogger(this.getClass());

	public ErrorCode error;

	// 게시물(자원)이 존재하지 않음
	public NotFoundException(ErrorCode error) {
		this.error = error;
		this.setStackTrace(new StackTraceElement[0]); // stackTrace를 비워준다.
	}

	public NotFoundException(ErrorCode error, Exception e) {
		this.error = error;
		e.printStackTrace();
		this.setStackTrace(new StackTraceElement[0]); // stackTrace를 비워준다.
	}
}
