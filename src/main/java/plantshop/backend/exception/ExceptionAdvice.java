package plantshop.backend.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.response.FailureInfo;
import plantshop.backend.response.BaseResponse;

@Slf4j
@RestControllerAdvice
public class ExceptionAdvice {

    @ExceptionHandler(GlobalException.class)
    protected BaseResponse globalException(GlobalException e){
        log.error("global exception", e);
        return new BaseResponse(e.getFailureInfo());
    }

    @ExceptionHandler(Exception.class)
    protected BaseResponse internalSeverException(Exception e){
        log.error("internal server exception", e);
        return new BaseResponse(FailureInfo.INTERNAL_SERVER_EXCEPTION);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected BaseResponse invalidInputException(MethodArgumentNotValidException e){
        log.error("invalid input exception" + e);
        return new BaseResponse(
                false,
                FailureInfo.INVALID_INPUT_EXCEPTION.getCode(),
                e.getFieldError().getDefaultMessage()
        );
    }
}
