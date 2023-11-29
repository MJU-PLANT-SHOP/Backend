package plantshop.backend.domain.purchase.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.AuditEntity;
import plantshop.backend.domain.product.entity.Product;
import plantshop.backend.domain.purchase.dto.request.PurchaseRequestDto;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"purchase_id", "product_id"})  // 동시성 문제로 인한 DB 중복 저장 방지
})
public class PurchaseDetail extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "purchase_id", nullable = false)
    private Purchase purchase;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(nullable = false)
    private Integer count;

    @Column(nullable = false)
    private Integer totalPrice;
}
