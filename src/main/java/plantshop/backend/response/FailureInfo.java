package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum FailureInfo {
    INTERNAL_SERVER_ERROR("99", "서버 오류가 발생했습니다."),
    INVALID_INPUT("98", "입력값이 유효하지 않습니다."),

    // 회원 (code : 10~ )
    ALREADY_EXISTENT_MEMBER("10", "이미 존재하는 회원입니다."),
    NOT_EXISTENT_MEMBER("11", "존재하지 않는 회원입니다."),
    NOT_MATCH_PASSWORD("12", "비밀번호가 일치하지 않습니다."),

    // 상품 (code: 20~ )
    NOT_EXISTENT_PRODUCT("20", "존재하지 않는 상품입니다."),
    // 장바구니 (code: 30~ )
    NOT_EXISTNET_CARTITEM("30", "존재하지 않는 장바구니 아이템입니다."),
    // 게시글(code: 40~ )
    NOT_EXISTENT_POST("40", "존재하지않는 게시글입니다.")

    ;
    private final String code;
    private final String message;

}
