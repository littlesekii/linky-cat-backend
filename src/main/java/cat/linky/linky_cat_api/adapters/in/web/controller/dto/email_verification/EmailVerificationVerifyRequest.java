package cat.linky.linky_cat_api.adapters.in.web.controller.dto.email_verification;

import cat.linky.linky_cat_api.core.ports.in.dto.email_verification.EmailVerificationVerifyCommand;

public record EmailVerificationVerifyRequest(
    String email, 
    String verificationCode
) {
    public EmailVerificationVerifyCommand toCommand() {
        return new EmailVerificationVerifyCommand(
            email,
            verificationCode
        );
    }
}