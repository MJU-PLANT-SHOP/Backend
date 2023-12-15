package plantshop.backend.domain.member.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenRequestDto {
    @Schema(description = "액세스 토큰")
    @NotBlank(message = "액세스 토큰을 입력해주세요.")
    private String accessToken;
    @Schema(description = "리프레시 토큰")
    @NotBlank(message = "리프레시 토큰을 입력해주세요.")
    private String refreshToken;
}
