package exception;

import common.ErrorCode;
import common.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: liushuai
 * @date: 2020/12/15
 * @description：
 */
@Slf4j
@RestControllerAdvice
public class MyExceptionAdvice {

    @ExceptionHandler(Exception.class)
    Result<Void> handleException(Exception e) {
        log.error("全局捕获异常", e);
        return new Result<>(ErrorCode.SYSTEM_ERROR);
    }
}
