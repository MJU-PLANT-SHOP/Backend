package plantshop.backend.domain.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantshop.backend.domain.cart.entity.Cart;
import plantshop.backend.domain.member.entity.Member;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findAllByMemberId(Long memberId);
    Optional<Cart> findByMemberIdAndProductId(Long memberId, Long productId);
}