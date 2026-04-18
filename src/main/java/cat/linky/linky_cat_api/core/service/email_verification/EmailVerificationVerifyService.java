package cat.linky.linky_cat_api.core.service.email_verification;

import cat.linky.linky_cat_api.core.domain.EmailVerification;
import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;
import cat.linky.linky_cat_api.core.exception.ResourceNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.email_verification.EmailVerificationVerifyCommand;
import cat.linky.linky_cat_api.core.ports.in.usecase.email_verification.EmailVerificationVerifyUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.EmailVerificationRepositoryPort;

public class EmailVerificationVerifyService implements EmailVerificationVerifyUseCase {

    private final EmailVerificationRepositoryPort repositoryPort;

    public EmailVerificationVerifyService(
        EmailVerificationRepositoryPort repositoryPort
    ) {
        this.repositoryPort = repositoryPort;
    }
    @Override
    public void execute(EmailVerificationVerifyCommand command) {
        String email = command.email().toLowerCase().trim();
        String verificationCode = command.verificationCode().toUpperCase().trim();

        EmailVerification emailVerification = repositoryPort.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("service.email_verification.not_found"));

        if (emailVerification.getIsVerified()) 
            throw new InvalidArgumentException("service.email_verification.already_verified");

        if (emailVerification.isExpired())
            throw new InvalidArgumentException("service.email_verification.expired");

        if (!verificationCode.matches("[A-Z0-9]{6}"))
            throw new InvalidArgumentException("service.email_verification.invalid");

        if (!verificationCode.equals(emailVerification.getVerificationCode()))
            throw new InvalidArgumentException("service.email_verification.invalid");

        emailVerification.verify();
        repositoryPort.save(emailVerification);
    }
}