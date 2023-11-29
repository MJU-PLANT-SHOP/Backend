package plantshop.backend.domain.cart.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.AuditEntity;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.product.entity.Product;
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"member_id", "product_id"})  // 동시성 문제로 인한 DB 중복 저장 방지
})
public class Cart extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false)
    private Integer count;

    public void updateCount(Integer count) {this.count = count;}
}
