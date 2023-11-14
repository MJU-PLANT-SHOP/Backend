package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    ;
    private final String code = "1";
    private final String message;

}
