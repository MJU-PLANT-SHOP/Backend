package plantshop.backend.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import plantshop.backend.domain.member.dto.request.SignUpRequestDto;
import plantshop.backend.domain.member.service.MemberService;
import plantshop.backend.response.BaseResponse;

import static plantshop.backend.response.SuccessInfo.SUCCESS_TO_SING_UP;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
@Tag(name = "회원 API 명세서", description = "회원 API 명세서")
public class MemberController {
    private final MemberService memberService;

    @Operation(summary = "회원가입 API", description = "회원가입 정보를 입력하세요.")
    @PostMapping("/sign-up")
    public BaseResponse singUp(@Valid @RequestBody SignUpRequestDto signUpRequestDto){
        memberService.singUp(signUpRequestDto);
        return new BaseResponse(SUCCESS_TO_SING_UP);
    }
}
