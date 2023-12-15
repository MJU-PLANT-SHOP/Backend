package plantshop.backend.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import plantshop.backend.response.FailureInfo;

@Getter
public class GlobalException extends RuntimeException{
    private final FailureInfo failureInfo;
    public GlobalException(FailureInfo failureInfo) {
        super(failureInfo.getMessage());
        this.failureInfo = failureInfo;
    }
}
