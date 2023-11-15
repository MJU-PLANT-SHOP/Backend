package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FailureInfo {
    INTERNAL_SERVER_EXCEPTION("99", "서버 오류가 발생했습니다."),
    INVALID_INPUT_EXCEPTION("98", "입력값이 유효하지 않습니다.");
    private final String code;
    private final String message;

}
