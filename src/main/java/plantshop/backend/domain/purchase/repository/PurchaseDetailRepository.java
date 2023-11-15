package plantshop.backend.domain.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantshop.backend.domain.purchase.entity.PurchaseDetail;

public interface PurchaseDetailRepository extends JpaRepository<PurchaseDetail, Long> {
}
