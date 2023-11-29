package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    SING_UP("회원가입이 완료되었습니다."),
    LOGIN("로그인이 성공하였습니다."),
    WRITE_POST("게시글 작성이 성공하였습니다."),
    GET_POST_LIST("게시글 목록 가져오기를 성공하였습니다"),
    GET_POST("게시글 가져오기를 성공하였습니다."),
    GET_CATEGORY("카테고리 가져오기를 성공하였습니다."),
    GET_PRODUCT_LIST("상품 목록 가져오기를 성공하였습니다."),
    GET_PRODUCT("상품 가져오기를 성공하였습니다."),
    TRY_PURCHASE("구매에 성공하였습니다.");
    private final String code = "1";
    private final String message;

}
