package plantshop.backend.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import plantshop.backend.domain.member.dto.request.SignUpRequestDto;
import plantshop.backend.domain.member.repository.MemberRepository;
import plantshop.backend.exception.GlobalException;
import plantshop.backend.response.FailureInfo;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void singUp(SignUpRequestDto signUpRequestDto) {
        if (memberRepository.existsByEmail(signUpRequestDto.getEmail()))
            throw new GlobalException(FailureInfo.EMAIL_DUPLICATION_EXCEPTION);
        memberRepository.save(signUpRequestDto.toEntity(passwordEncoder));
    }
}
