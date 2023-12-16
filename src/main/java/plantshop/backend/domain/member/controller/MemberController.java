package plantshop.backend.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import plantshop.backend.domain.member.dto.request.SignInRequestDto;
import plantshop.backend.domain.member.dto.request.SignUpRequestDto;
import plantshop.backend.domain.member.dto.request.TokenRequestDto;
import plantshop.backend.domain.member.dto.response.GetMyInfoResponseDto;
import plantshop.backend.domain.member.dto.response.SignInResponseDto;
import plantshop.backend.domain.member.dto.response.TokenResponseDto;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.response.BaseResponse;
import plantshop.backend.response.DataResponse;

import static plantshop.backend.response.SuccessInfo.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "회원 API 명세서", description = "회원 API 명세서")
@Validated
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "회원가입 정보를 입력하세요.")
    @PostMapping("/sign-up")
    public BaseResponse singUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        memberService.singUp(signUpRequestDto);
        return new BaseResponse(SING_UP);
    }

    @Operation(summary = "로그인 API", description = "로그인 정보를 입력하세요.")
    @PostMapping("/sign-in")
    public DataResponse<SignInResponseDto> singIn(@Valid @RequestBody SignInRequestDto signInRequestDto){
        return new DataResponse<>(LOGIN, memberService.signIn(signInRequestDto));
    }

    @Operation(summary = "내 정보 가져오기 API", description = "내 정보 가져오기 API 입니다.")
    @GetMapping("/me")
    public DataResponse<GetMyInfoResponseDto> getMyInfo() {
        return new DataResponse<>(GET_MY_INFO, memberService.getMyInfo());
    }

    @Operation(summary = "토큰 재발행 API", description = "토큰 정보를 입력하세요.")
    @PostMapping("/reissue")
    public DataResponse<TokenResponseDto> reissue(@Valid @RequestBody TokenRequestDto tokenRequestDto) {
        return new DataResponse<>(REISSUE, memberService.reissue(tokenRequestDto));
    }
    @Operation(summary = "이메일 중복 검사 API", description = "이메일을 입력하세요.")
    @GetMapping("/check-email")
    public BaseResponse checkEmail(
            @NotBlank(message = "이메일을 입력해주세요.")
            @Email(message = "유효한 이메일 주소가 아닙니다.")
            String email) {
        memberService.checkEmail(email);
        return new BaseResponse(CHECK_EMAIL);
    }
}
