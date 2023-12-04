package plantshop.backend.domain.cart.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.cart.entity.Cart;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetCartListResponseDto {
    @Schema(description = "상품 아이디", defaultValue = "1")
    private Long id;
    public static GetCartListResponseDto from(Cart cart) {
        return GetCartListResponseDto.builder()
                .id(cart.getId())
                .build();
    }
}
