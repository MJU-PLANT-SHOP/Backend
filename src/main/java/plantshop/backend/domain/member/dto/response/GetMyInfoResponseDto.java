package plantshop.backend.domain.member.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMyInfoResponseDto {
    @Schema(description = "아이디", defaultValue = "1")
    private Long id;
    @Schema(description = "이메일", defaultValue = "test@gmail.com")
    private String email;
    @Schema(description = "이름", defaultValue = "김철수")
    private String name;
    @Schema(description = "휴대폰 번호", defaultValue = "01012345678")
    private String phone;
    @Schema(description = "주소", defaultValue = "서울특별시 서대문구 거북골로 34")
    private String address;
}
