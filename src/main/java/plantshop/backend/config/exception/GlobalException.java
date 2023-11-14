package plantshop.backend.config.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import plantshop.backend.config.response.FailureResponseInfo;

@RequiredArgsConstructor
@Getter
public class GlobalException extends RuntimeException{
    private final FailureResponseInfo failureResponseInfo;
}
