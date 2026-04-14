package cat.linky.linky_cat_api.core.service.email_verification;

import java.security.SecureRandom;
import java.time.Duration;
import java.time.Instant;

import cat.linky.linky_cat_api.core.domain.EmailVerification;
import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;
import cat.linky.linky_cat_api.core.exception.TooManyRequestsException;
import cat.linky.linky_cat_api.core.ports.in.dto.email_verification.EmailVerificationSendCommand;
import cat.linky.linky_cat_api.core.ports.in.usecase.email_verification.EmailVerificationSendUseCase;
import cat.linky.linky_cat_api.core.ports.out.mail.MailSenderPort;
import cat.linky.linky_cat_api.core.ports.out.repository.EmailVerificationRepositoryPort;

public class EmailVerificationSendService implements EmailVerificationSendUseCase {

    private final EmailVerificationRepositoryPort repositoryPort;
    private final MailSenderPort mailSenderPort;

    public EmailVerificationSendService(
        EmailVerificationRepositoryPort repositoryPort,
        MailSenderPort mailSenderPort
    ) {
        this.repositoryPort = repositoryPort;
        this.mailSenderPort = mailSenderPort;
    }

    @Override
    public void execute(EmailVerificationSendCommand command) {
        String email = command.email().toLowerCase().trim();
        
        EmailVerification emailVerification = repositoryPort.findByEmail(email)
            .orElse(null);
        String verificationCode = generateVerificationCode(6);

        if (emailVerification != null) {

            if (emailVerification.getIsVerified()) 
                throw new InvalidArgumentException("email is already verified");

            long secondsSinceLastSentVerification = Duration.between(
                emailVerification.getUpdatedAt(), 
                Instant.now()
            ).getSeconds();

            if (secondsSinceLastSentVerification < 60) {
                throw new TooManyRequestsException("please wait " + (60 - secondsSinceLastSentVerification) + " seconds before send another verification");
            }

            emailVerification.updateVerificationCode(verificationCode);            
        } else {
            emailVerification = new EmailVerification(
                email,
                verificationCode
            );
        }

        repositoryPort.save(emailVerification);
        mailSenderPort.sendMail(
            email, 
            "Email verification code", 
            "Your email verification code: " + verificationCode + "\n\n This code is valid for 10 minutes."
        );
    }

    private String generateVerificationCode(int charCount) {
        String charset = "23456789ABCDEFGHJKLMNPQRSTUVWXYZ";
        SecureRandom random = new SecureRandom();

        StringBuilder verificationCode = new StringBuilder(charCount);
        for (int i = 0; i < charCount; i++) {
            int index = random.nextInt(charset.length());
            verificationCode.append(charset.charAt(index));
        }
        return verificationCode.toString();
    }  
}