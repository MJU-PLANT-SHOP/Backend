package plantshop.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantshop.backend.config.jwt.JwtProvider;
import plantshop.backend.config.jwt.TokenDto;
import plantshop.backend.domain.member.dto.request.LoginRequestDto;
import plantshop.backend.domain.member.dto.request.SignUpRequestDto;
import plantshop.backend.domain.member.dto.response.LoginResponseDto;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.repository.MemberRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public void singUp(SignUpRequestDto signUpRequestDto) {
        if(memberRepository.existsByEmail(signUpRequestDto.getEmail()))
            throw new GlobalException(FailureInfo.ALREADY_EXISTENT_MEMBER);
        memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
    }

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail())
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTENT_MEMBER));
        if(!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword()))
            throw new GlobalException(FailureInfo.NOT_MATCH_PASSWORD);

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(member.getEmail(), member.getId()));

        TokenDto tokenDto = jwtProvider.generateTokenDto(authentication);

        return LoginResponseDto.builder()
                .accessToken(tokenDto.getAccessToken())
                .refreshToken(tokenDto.getRefreshToken())
                .build();
    }

    public Member getCurrentMember(){
        return memberRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTENT_MEMBER));
    }
}
