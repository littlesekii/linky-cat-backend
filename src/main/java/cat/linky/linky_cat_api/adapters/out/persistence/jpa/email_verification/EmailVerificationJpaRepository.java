package cat.linky.linky_cat_api.adapters.out.persistence.jpa.email_verification;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailVerificationJpaRepository extends JpaRepository<EmailVerificationJpaEntity, UUID>  {
    public Optional<EmailVerificationJpaEntity> findByEmail(String email);
}
