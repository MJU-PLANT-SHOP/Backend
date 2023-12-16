package plantshop.backend.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckEmailRequestDto {
    @NotBlank(message = "이메일을 입력해주세요.")
    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    @Email(message = "유효한 이메일 주소가 아닙니다.")
    private String email;
}
