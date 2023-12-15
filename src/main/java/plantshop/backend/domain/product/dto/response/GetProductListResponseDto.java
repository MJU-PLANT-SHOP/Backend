package plantshop.backend.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.product.entity.Category;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.product.entity.ProductImage;

import java.awt.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductListResponseDto {

    @Schema(description = "상품 ID")
    private Long id;

    @Schema(description = "카테고리")
    private Category category;

    @Schema(description = "상품 이름")
    private String name;

    @Schema(description = "상품 가격")
    private Integer price;

    @Schema(description = "상품 이미지")
    private  String imageUrl;

    public static GetProductListResponseDto from(Product product){
        return GetProductListResponseDto.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .category(product.getCategory())
                .imageUrl(product.getProductImageList().get(0).getUrl())
                .build();
    }
}
