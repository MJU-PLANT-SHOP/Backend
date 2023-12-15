package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {

    //멤버
    SING_UP("회원가입이 완료되었습니다."),
    LOGIN("로그인이 성공하였습니다."),
    GET_MY_INFO("내 정보 가져오기를 성공하였습니다."),
    REISSUE("토큰 재발행을 성공하였습니다"),

    //장바구니
    ADD_CART("장바구니에 물품을 추가하였습니다."),
    UPDATE_CART("장바구니 물품 갯수를 수정했습니다."),
    GET_CART("장바구니 목록 가져오기를 성공했습니다."),
    DELETE_CART("장바구니 삭제에 성공했습니다."),

    //상품
    GET_CATEGORY("카테고리 가져오기를 성공하였습니다."),
    GET_PRODUCT_LIST("상품 목록 가져오기를 성공하였습니다."),
    GET_PRODUCT("상품 가져오기를 성공하였습니다."),
    TRY_PURCHASE("구매에 성공하였습니다.");
    private final String code = "1";
    private final String message;
}