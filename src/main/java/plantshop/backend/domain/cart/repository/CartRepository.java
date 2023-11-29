package plantshop.backend.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantshop.backend.domain.cart.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
