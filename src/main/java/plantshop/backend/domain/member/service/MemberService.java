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
import plantshop.backend.domain.member.dto.request.SignInRequestDto;
import plantshop.backend.domain.member.dto.request.SignUpRequestDto;
import plantshop.backend.domain.member.dto.request.TokenRequestDto;
import plantshop.backend.domain.member.dto.response.GetMyInfoResponseDto;
import plantshop.backend.domain.member.dto.response.SignInResponseDto;
import plantshop.backend.domain.member.dto.response.TokenResponseDto;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.member.entity.RefreshToken;
import plantshop.backend.domain.member.repository.MemberRepository;
import plantshop.backend.domain.member.repository.RefreshTokenRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final RefreshTokenRepository refreshTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;


    public void singUp(SignUpRequestDto signUpRequestDto) {
        if(memberRepository.existsByEmail(signUpRequestDto.getEmail()))
            throw new GlobalException(FailureInfo.ALREADY_EXISTENT_MEMBER);
        memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
    }

    public SignInResponseDto signIn(SignInRequestDto signInRequestDto) {
        Member member = memberRepository.findByEmail(signInRequestDto.getEmail())
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTENT_MEMBER));
        if(!passwordEncoder.matches(signInRequestDto.getPassword(), member.getPassword()))
            throw new GlobalException(FailureInfo.NOT_MATCH_PASSWORD);

        Authentication authentication = authenticationManagerBuilder.getObject()
                .authenticate(new UsernamePasswordAuthenticationToken(member.getEmail(), member.getId()));

        TokenResponseDto tokenResponseDto = publishToken(authentication);

        return SignInResponseDto.builder()
                .accessToken(tokenResponseDto.getAccessToken())
                .refreshToken(tokenResponseDto.getRefreshToken())
                .build();
    }

    public GetMyInfoResponseDto getMyInfo() {
        Member member = getCurrentMember();
        return GetMyInfoResponseDto.builder()
                .id(member.getId())
                .name(member.getName())
                .phone(member.getPhone())
                .email(member.getEmail())
                .address(member.getAddress())
                .build();
    }

    public Member getCurrentMember(){
        return memberRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new GlobalException(FailureInfo.NOT_EXISTENT_MEMBER));
    }


    public TokenResponseDto reissue(TokenRequestDto tokenRequestDto) {
        String refreshToken = tokenRequestDto.getRefreshToken();

        Authentication authentication;

        try {
            authentication = jwtProvider.getAuthentication(tokenRequestDto.getAccessToken());
        }catch (Exception e){
            throw new GlobalException(FailureInfo.INVALID_ACCESS_TOKEN);
        }

        String email = authentication.getName();

        if (!jwtProvider.validateToken(refreshToken))
            throw new GlobalException(FailureInfo.INVALID_REFRESH_TOKEN);

        refreshTokenRepository.findByKeyAndValue(email, refreshToken)
                .orElseThrow(()->new GlobalException(FailureInfo.NOT_EXISTENT_REFRESH_TOKEN));

        return publishToken(authentication);
    }

    private TokenResponseDto publishToken(Authentication authentication) {
        TokenDto generatedTokenDto = jwtProvider.generateTokenDto(authentication);
        String generatedRefreshToken = generatedTokenDto.getRefreshToken();
        refreshTokenRepository.save(
                RefreshToken.builder()
                .key(authentication.getName())
                .value(generatedRefreshToken)
                .build()
        );

        return TokenResponseDto.builder()
                .accessToken(generatedTokenDto.getAccessToken())
                .refreshToken(generatedTokenDto.getRefreshToken())
                .build();
    }

    public void checkEmail(String email) {
        if(memberRepository.existsByEmail(email))
            throw new GlobalException(FailureInfo.ALREADY_EXISTENT_MEMBER);
    }
}
