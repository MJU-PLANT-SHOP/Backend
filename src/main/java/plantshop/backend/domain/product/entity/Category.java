package plantshop.backend.domain.product.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Category {
    AIR_PURITY_PLANT("공기정화식물"),
    CACTUS("다육식물"),
    FLOWER("꽃"),
    POT("분재");
    private final String description;

}
