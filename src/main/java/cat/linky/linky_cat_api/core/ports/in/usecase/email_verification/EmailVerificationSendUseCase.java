package cat.linky.linky_cat_api.core.ports.in.usecase.email_verification;

import cat.linky.linky_cat_api.core.ports.in.dto.email_verification.EmailVerificationSendCommand;

public interface EmailVerificationSendUseCase {
    void execute(EmailVerificationSendCommand command);
}