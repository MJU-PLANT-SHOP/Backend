package plantshop.backend.domain.order.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import plantshop.backend.domain.member.entity.Member;

import java.util.ArrayList;

import static jakarta.persistence.CascadeType.ALL;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Purchase {
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
    @OneToMany(mappedBy = "purchase", cascade = ALL, orphanRemoval = true)
    private ArrayList<PurchaseDetail> arrayList = new ArrayList<>();
}
