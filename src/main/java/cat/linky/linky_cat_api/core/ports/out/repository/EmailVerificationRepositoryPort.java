package cat.linky.linky_cat_api.core.ports.out.repository;

import java.util.Optional;

import cat.linky.linky_cat_api.core.domain.EmailVerification;

public interface EmailVerificationRepositoryPort {
    public Optional<EmailVerification> findByEmail(String email);
    public EmailVerification save(EmailVerification emailVerification);
}
