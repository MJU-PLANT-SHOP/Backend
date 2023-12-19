package plantshop.backend.domain.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantshop.backend.domain.member.entity.Member;
import plantshop.backend.domain.purchase.entity.Purchase;
import plantshop.backend.domain.purchase.entity.Status;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
    Integer countByMemberAndStatus(Member member, Status completePayment);
}
