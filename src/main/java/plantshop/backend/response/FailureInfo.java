package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FailureInfo {
    INTERNAL_SERVER_EXCEPTION("99", "서버 오류가 발생했습니다."),
    INVALID_INPUT_EXCEPTION("98", "입력값이 유효하지 않습니다."),

    // 회원 (code : 10~ )
    EMAIL_DUPLICATION_EXCEPTION("10", "이미 존재하는 이메일입니다."),

    // 상품 (code: 20~ )

    // 장바구니 (code: 30~ )

    // 주문 (code: 40~ )
    ;
    private final String code;
    private final String message;

}
