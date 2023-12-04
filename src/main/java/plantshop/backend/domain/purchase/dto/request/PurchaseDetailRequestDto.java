package plantshop.backend.domain.purchase.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.product.repository.ProductRepository;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.entity.PurchaseDetail;
import plantshop.backend.exception.GlobalException;

import java.util.List;
import java.util.stream.Collectors;

import static plantshop.backend.response.FailureInfo.NOT_EXISTENT_PRODUCT;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDetailRequestDto {

    @NotBlank(message = "수량이 없습니다.")
    @Schema(description = "수량", defaultValue = "1")
    private Integer count;

    @NotBlank(message = "주문번호가 존재하지 않습니다.")
    @Schema(description = "주문 번호", defaultValue = "1")
    private Purchase purchase;

    @NotNull(message = "존재하지 않는 제품 번호 입니다.")
    @Schema(description = "제품 번호", defaultValue = "1")
    private Long productId;



    public PurchaseDetail toEntity(Purchase purchase, ProductRepository productRepository) {
                PurchaseDetail purchaseDetail = PurchaseDetail.builder()
                .purchase(purchase)
                .product(productRepository.findById(this.productId).orElseThrow(()-> new GlobalException(NOT_EXISTENT_PRODUCT)))
                .count(this.count)
                .totalPrice(productRepository.findPriceById(this.productId) * this.count)
                .build();
        return purchaseDetail;
    }
}
