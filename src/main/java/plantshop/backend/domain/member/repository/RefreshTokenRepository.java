package plantshop.backend.domain.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import plantshop.backend.domain.member.entity.RefreshToken;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, String> {
    Optional<RefreshToken> findByKeyAndValue(String email, String refreshToken);
}
