package plantshop.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import plantshop.backend.response.FailureInfo;

@RequiredArgsConstructor
@Getter
public class GlobalException extends RuntimeException{
    private final FailureInfo failureInfo;
}
