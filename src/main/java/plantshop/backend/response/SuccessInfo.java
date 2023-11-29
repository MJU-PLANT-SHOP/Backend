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
    ADD_CART("장바구니에 물품을 추가하였습니다."),
    UPDATE_CART("장바구니 물품 갯수를 수정했습니다."),
    GET_CART("장바구니 목록 가져오기를 성공했습니다."),
    DELETE_CART("장바구니 삭제에 성공했습니다.");
    private final String code = "1";
    private final String message;
}