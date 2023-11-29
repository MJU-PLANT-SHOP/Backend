package plantshop.backend.domain.product.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.product.entity.Category;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.product.entity.ProductImage;
import plantshop.backend.domain.product.entity.Size;

import java.util.ArrayList;
import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetProductResponseDto {

    @Schema(description = "상품 ID")
    private Long id;
    @Schema(description = "카테고리")
    private Category category;
    @Schema(description = "상품 이름")
    private String name;
    @Schema(description = "상품 가격")
    private Integer price;
    @Schema(description = "상품 사이즈")
    private Size size;
    @Schema(description = "상품 설명")
    private String description;
    @Schema(description = "상품 이미지")
    private List<String> imageUrlList;
    public static GetProductResponseDto from(Product product){
        List<String> imageUrlList = new ArrayList<>();
        for(int i = 0; i<product.getProductImageList().size(); i++){
            imageUrlList.add(product.getProductImageList().get(i).getUrl());
        }

        return GetProductResponseDto.builder()
                .id(product.getId())
                .category(product.getCategory())
                .name(product.getName())
                .price(product.getPrice())
                .size(product.getSize())
                .description(product.getDescription())
                .imageUrlList(imageUrlList)
                .build();
    }
}
