package cat.linky.linky_cat_api.core.domain;

import java.time.Duration;
import java.time.Instant;
import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;

public class EmailVerification {
    
    private UUID id;
    private String email;
    private String verificationCode;
    private Boolean isVerified;
    private Instant expiresAt;
    private Instant createdAt;
    private Instant updatedAt;

    public EmailVerification() {}
    public EmailVerification(UUID id, String email, String verificationCode, Boolean isVerified, Instant expiresAt, 
            Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.email = email;
        this.verificationCode = verificationCode;
        this.isVerified = isVerified;
        this.expiresAt = expiresAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        validate();
    }
    public EmailVerification(String email, String verificationCode) {
        this.id = null;
        this.email = email;
        this.verificationCode = verificationCode;
        this.isVerified = false;

        Instant now = Instant.now();
        this.expiresAt = now.plus(Duration.ofMinutes(10L));
        this.createdAt = now;
        this.updatedAt = now;
        validate();
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

    public Instant getExpiresAt() {
        return expiresAt;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }
    
    public Instant getUpdatedAt() {
        return updatedAt;
    }

    public boolean isExpired() {
        return Instant.now().isAfter(this.expiresAt);
    }

    public void verify() {
        this.isVerified = true;

        Instant now = Instant.now();
        updateUpdatedAt(now);
    }

    public void updateVerificationCode(String verificationCode) {
        if (verificationCode != null) {
            if (verificationCode.isEmpty())
                throw new InvalidArgumentException("domain.email_verification.verification_code.blank");
        }

        this.verificationCode = verificationCode;

        Instant now = Instant.now();
        updateUpdatedAt(now);
        updateExpiresAt(now);
    }

    private void updateUpdatedAt(Instant moment) {
        this.updatedAt = moment;
    }

    private void updateExpiresAt(Instant moment) {
        this.expiresAt = moment.plus(Duration.ofMinutes(10L));
    }

    public void validate() {
        if (email == null || email.isEmpty())
            throw new InvalidArgumentException("domain.email_verification.email.blank");

        if (verificationCode == null || verificationCode.isEmpty())
            throw new InvalidArgumentException("domain.email_verification.verification_code.blank");
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        EmailVerification other = (EmailVerification) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
}
