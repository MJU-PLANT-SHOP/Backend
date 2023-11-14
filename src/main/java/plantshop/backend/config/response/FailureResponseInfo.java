package plantshop.backend.config.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FailureResponseInfo {
    INTERNAL_SERVER_EXCEPTION("999", "서버 오류가 발생했습니다."),
    INVALID_INPUT_EXCEPTION("998", "입력값이 유효하지 않습니다.");
    private final String code;
    private final String message;

}
