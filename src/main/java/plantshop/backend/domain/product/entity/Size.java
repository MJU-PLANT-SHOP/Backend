package plantshop.backend.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Size {
    SMALL("소형"),
    MEDIUM("중형"),
    LARGE("대형");
    private final String description;

}
