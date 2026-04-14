package cat.linky.linky_cat_api.adapters.in.web.controller.dto.email_verification;

import cat.linky.linky_cat_api.core.ports.in.dto.email_verification.EmailVerificationSendCommand;

public record EmailVerificationSendRequest(
    String email
) {
    public EmailVerificationSendCommand toCommand() {
        return new EmailVerificationSendCommand(email);
    }
}