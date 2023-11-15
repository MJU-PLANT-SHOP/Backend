package plantshop.backend.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum SuccessInfo {
    SUCCESS_TO_SING_UP("회원가입이 완료되었습니다."),
    SUCCESS_TO_SING_IN("로그인이 성공하였습니다.");
    private final String code = "1";
    private final String message;

}
