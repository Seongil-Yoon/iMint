package multi.fclass.iMint.common.exception;

//Http Status Code
//2XX -> OK 서버측에 요청된 처리가 정상처리됨을 알린다
//4XX -> 클라이언트가 잘못된 요청을 했을때. 권한이 없는것을 요청 혹은 없는 페이지
//5XX -> 서버측에서 오류.

//@ResponseStatus(HttpStatus.NOT_FOUND) 사용하여
//없는 사용자를 요청했을때 5xx으로 처리하던거를 NotFound라는 값을 전달하도록 바꾼다
////NOT FOUND했기때문에 5XX에러가 아닌 404에러로 처리한다

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    // RuntimeException 프로그램 실행중 오류 개발자가 의도적 으로 잡아 내기 위한 조건에 부합 할떄 발생
    public NotFoundException(String message) {
        super(message);
    }
}
