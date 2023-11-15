package plantshop.backend.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import plantshop.backend.domain.member.entity.Member;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpRequestDto {
    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    @NotBlank(message = "이메일을 입력해주세요.")
    private String email;
    @Schema(description = "비밀번호", defaultValue = "test1234")
    @NotBlank(message = "비밀번호를 입력해주세요.")
    private String password;
    @Schema(description = "이름", defaultValue = "김철수")
    @NotBlank(message = "이름을 입력해주세요.")
    private String name;
    @Schema(description = "휴대폰 번호", defaultValue = "01012345678")
    @NotBlank(message = "휴대폰 번호를 입력해주세요.")
    private String phone;
    @Schema(description = "주소", defaultValue = "서울특별시 서대문구 거북골로 34")
    @NotBlank(message = "주소를 입력해주세요.")
    private String address;

    public Member toEntity(PasswordEncoder passwordEncoder){
        return Member.builder()
                .email(email)
                .password(passwordEncoder.encode(password))
                .name(name)
                .phone(phone)
                .address(address)
                .build();
    }
}
