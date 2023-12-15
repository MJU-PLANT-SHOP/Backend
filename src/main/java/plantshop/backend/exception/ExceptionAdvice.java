package plantshop.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plantshop.backend.response.FailureInfo;
import plantshop.backend.response.BaseResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GlobalException.class)
    protected BaseResponse globalException(GlobalException e){
        log.error("global exception.", e);
        return new BaseResponse(e.getFailureInfo());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected BaseResponse internalSeverException(Exception e){
        log.error("internal server exception.", e);
        return new BaseResponse(FailureInfo.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse invalidInputException(MethodArgumentNotValidException e){
        log.error("invalid input exception." + e);
        return new BaseResponse(
                false,
                FailureInfo.INVALID_INPUT.getCode(),
                e.getFieldError().getDefaultMessage()
        );
    }
}
