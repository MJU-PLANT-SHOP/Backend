package plantshop.backend.config.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessResponseInfo {
    ;
    private final String code = "1";
    private final String message;

}
