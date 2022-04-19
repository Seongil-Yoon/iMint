package multi.fclass.iMint.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
    //권한 없는 요청 차단
    public UnauthorizedException(String message){
        super(message);
    }

}
