package plantshop.backend.domain.purchase.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.entity.PurchaseDetail;
import plantshop.backend.domain.purchase.entity.Status;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseRequestDto {

    @NotBlank(message = "상품이 없습니다.")
    @Schema(description = "배송지", defaultValue = "서대문구")
    private String deliveryAddress;

    @NotBlank(message = "상품이 없습니다.")
    @Schema(description = "배송장소", defaultValue = "문 앞")
    private String pickUpLocation;

    @NotBlank(message = "상품이 없습니다.")
    @Schema(description = "요구사항", defaultValue = "없음")
    private String requirement;

    @NotBlank(message = "상품이 없습니다.")
    @Schema(description = "총 가격", defaultValue = "0")
    private Integer totalPrice;

    @NotBlank(message = "상품이 없습니다.")
    @Schema(description = "주문 상태", defaultValue = "COMPLETE_PAYMENT")
    private Status status;

    @NotBlank(message = "제품이 존재 하지 않습니다.")
    @Schema(description = "주문 상세", defaultValue = "[\n" +
            "    {\n" +
            "      \"product\": {\"id\": 1},\n" +
            "      \"count\": 2,\n" +
            "      \"totalPrice\": 10.99\n" +
            "    }\n" +
            "  ]")
    private List<PurchaseDetailRequestDto> purchaseDetailList;


    public Purchase toEntity(Member member) {
        Purchase purchase = Purchase.builder()
                .member(member)
                .deliveryAddress(this.deliveryAddress)
                .pickUpLocation(this.pickUpLocation)
                .requirement(this.requirement)
                .totalPrice(this.totalPrice)
                .status(this.status)
                .build();
        List<PurchaseDetail> purchaseDetails = this.purchaseDetailList.stream()
                .map(PurchaseDetailRequestDto -> PurchaseDetailRequestDto.toEntity(purchase))
                .collect(Collectors.toList());
        purchase.setPurchaseDetailList(purchaseDetails);
        return purchase;
    }
}


