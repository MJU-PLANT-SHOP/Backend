package plantshop.backend.domain.order.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Status {
    COMPLETE_PAYMENT("결제완료"),
    PREPARING_PRODUCT("상품 준비중"),
    SHIPPING("배송중"),
    COMPLETE_SHIPPING("배송완료");

    private final String description;
}
