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
public class GetProductListForHomepageSecondMenuResponseDto {

    @Schema(description = "상품 ID")
    private Long id;

    @Schema(description = "상품 이미지")
    private  String imageUrl;

    public static GetProductListForHomepageSecondMenuResponseDto from(Product product){
        return GetProductListForHomepageSecondMenuResponseDto.builder()
                .id(product.getId())
                .imageUrl(product.getProductImageList().get(0).getUrl())
                .build();
    }
}

