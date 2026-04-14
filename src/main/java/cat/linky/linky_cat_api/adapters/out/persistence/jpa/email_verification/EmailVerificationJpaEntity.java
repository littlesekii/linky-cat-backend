package cat.linky.linky_cat_api.adapters.out.persistence.jpa.email_verification;

import java.time.Instant;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.EmailVerification;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_email_verification")
public class EmailVerificationJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String email;
    private String verificationCode;
    private Boolean isVerified;
    private Instant expiresAt;
    private Instant createdAt;
    private Instant updatedAt;

    public EmailVerificationJpaEntity() {}
    public EmailVerificationJpaEntity(UUID id, String email, String verificationCode, Boolean isVerified,
            Instant expiresAt, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.email = email;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public Boolean getIsVerified() {
        return isVerified;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }
    
    public EmailVerification toDomain() {
        return new EmailVerification(
            id,
            email,
            verificationCode,
            isVerified,
            expiresAt,
            createdAt,
            updatedAt
        );
    }

    public static EmailVerificationJpaEntity fromDomain(EmailVerification emailVerification) {
        return new EmailVerificationJpaEntity(
            emailVerification.getId(),
            emailVerification.getEmail(),
            emailVerification.getVerificationCode(),
            emailVerification.getIsVerified(),
            emailVerification.getExpiresAt(),
            emailVerification.getCreatedAt(),
            emailVerification.getUpdatedAt()
        );
    }    
}
