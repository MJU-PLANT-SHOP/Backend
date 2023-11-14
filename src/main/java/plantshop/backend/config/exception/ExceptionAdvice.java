package plantshop.backend.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plantshop.backend.config.response.FailureResponseInfo;
import plantshop.backend.config.response.Response;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(Exception.class)
    protected Response internalSeverException(Exception e){
        log.error("internal server exception", e);
        return Response.failure(FailureResponseInfo.INTERNAL_SERVER_EXCEPTION);
    }

    @ExceptionHandler(GlobalException.class)
    protected Response globalException(GlobalException e){
        log.error("global exception", e);
        return Response.failure(e.getFailureResponseInfo());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected Response invalidInputException(MethodArgumentNotValidException e){
        log.error("invalid input exception" + e);
        return Response.failure(
                FailureResponseInfo.INVALID_INPUT_EXCEPTION.getCode(),
                e.getFieldError().getDefaultMessage()
        );
    }
}
