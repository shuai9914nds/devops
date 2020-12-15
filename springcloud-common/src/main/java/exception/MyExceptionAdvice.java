package exception;

import common.ErrorCode;
import common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: liushuai
 * @date: 2020/12/15
 * @description：
 */
@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(Exception.class)
    Result<Void> handleException(Exception e) {
        return new Result<>(ErrorCode.SYSTEM_ERROR);
    }
}
