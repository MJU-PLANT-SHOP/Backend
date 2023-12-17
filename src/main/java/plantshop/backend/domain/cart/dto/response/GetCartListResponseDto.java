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
    @Schema(description = "상품 이름", defaultValue = "금전수")
    private String productName;
    @Schema(description = "상품 갯수", defaultValue = "1")
    private Integer count;
    @Schema(description = "상품 개당 가격", defaultValue = "1")
    private Integer price;
    public static GetCartListResponseDto from(Cart cart) {
        return GetCartListResponseDto.builder()
                .productName(cart.getProduct().getName())
                .count(cart.getCount())
                .price(cart.getProduct().getPrice())
                .build();
    }
}