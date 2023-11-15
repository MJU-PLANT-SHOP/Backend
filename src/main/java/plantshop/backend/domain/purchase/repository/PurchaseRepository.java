package plantshop.backend.domain.purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantshop.backend.domain.purchase.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, Long> {
}
