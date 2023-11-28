package plantshop.backend.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.product.entity.Category;
import plantshop.backend.domain.product.entity.Product;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetRecommendProductListDto {
    @Schema(description = "상품 ID")
    private Long id;

    @Schema(description = "상품 이름")
    private String name;

    @Schema(description = "상품 가격")
    private Integer price;

    @Schema(description = "상품 이미지")
    private  String imageUrl;

    public static GetRecommendProductListDto from(Product product){
        return GetRecommendProductListDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .imageUrl(product.getProductImageList().get(0).getUrl())
                .build();
    }
}
