package plantshop.backend.domain.purchase.entity;

import jakarta.persistence.*;
import lombok.*;
import plantshop.backend.domain.AuditEntity;
import plantshop.backend.domain.member.entity.Member;

import java.util.ArrayList;
import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Purchase extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;
    @Column(nullable = false)
    private String deliveryAddress;
    @Column(nullable = false)
    private String pickUpLocation;
    @Column(nullable = false)
    private String requirement;
    @Column(nullable = false)
    private Integer totalPrice;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(mappedBy = "purchase", cascade = {CascadeType.ALL, CascadeType.DETACH}, orphanRemoval = true)
    private List<PurchaseDetail> purchaseDetailList = new ArrayList<>();

}
