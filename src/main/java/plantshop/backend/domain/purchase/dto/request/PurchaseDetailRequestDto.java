package plantshop.backend.domain.purchase.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.entity.PurchaseDetail;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailRequestDto {

    @NotBlank(message = "수량이 없습니다.")
    @Schema(description = "수량", defaultValue = "1")
    private Integer count;

    @NotBlank(message = "상품이 없습니다.")
    @Schema(description = "총 가격", defaultValue = "0")
    private Integer totalPrice;

//    @NotBlank(message = "주문번호가 존재하지 않습니다.")
//    @Schema(description = "주문 번호", defaultValue = "1")
//    private Purchase purchase;

    @NotBlank(message = "존재하지 않는 제품 번호 입니다.")
    @Schema(description = "제품 번호", defaultValue = "1")
    private Product product;

    public PurchaseDetail toEntity(Purchase purchase) {
        return PurchaseDetail.builder()
                .purchase(purchase)
                .totalPrice(this.totalPrice)
                .count(this.count)
                .product(this.product)
                .build();
    }
}
