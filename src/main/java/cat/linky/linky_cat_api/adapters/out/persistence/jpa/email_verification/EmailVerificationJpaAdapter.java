package cat.linky.linky_cat_api.adapters.out.persistence.jpa.email_verification;

import java.util.Optional;

import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.domain.EmailVerification;
import cat.linky.linky_cat_api.core.ports.out.repository.EmailVerificationRepositoryPort;

@Component
public class EmailVerificationJpaAdapter implements EmailVerificationRepositoryPort {

    private final EmailVerificationJpaRepository repository;

    public EmailVerificationJpaAdapter(EmailVerificationJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<EmailVerification> findByEmail(String email) {
        Optional<EmailVerificationJpaEntity> entity = repository.findByEmail(email);
        return entity.map(EmailVerificationJpaEntity::toDomain);
    }
    
    @Override
    public EmailVerification save(EmailVerification emailVerification) {
        EmailVerificationJpaEntity entity = EmailVerificationJpaEntity.fromDomain(emailVerification);
        EmailVerificationJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }   
}
