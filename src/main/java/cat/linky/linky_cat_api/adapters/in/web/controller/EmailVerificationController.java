package cat.linky.linky_cat_api.adapters.in.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.StandardResponse;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.email_verification.EmailVerificationSendRequest;
import cat.linky.linky_cat_api.adapters.in.web.controller.dto.email_verification.EmailVerificationVerifyRequest;
import cat.linky.linky_cat_api.core.ports.in.usecase.email_verification.EmailVerificationSendUseCase;
import cat.linky.linky_cat_api.core.ports.in.usecase.email_verification.EmailVerificationVerifyUseCase;

@RestController
@RequestMapping("/api/email-verification")
public class EmailVerificationController {

    private final EmailVerificationSendUseCase emailVerificationSendUseCase;
    private final EmailVerificationVerifyUseCase emailVerificationVerifyUseCase;
    

    public EmailVerificationController(
        EmailVerificationSendUseCase emailVerificationSendUseCase,
        EmailVerificationVerifyUseCase emailVerificationVerifyUseCase
    ) {
        this.emailVerificationSendUseCase = emailVerificationSendUseCase;
        this.emailVerificationVerifyUseCase = emailVerificationVerifyUseCase;
    }

    @PostMapping("/send")
    public ResponseEntity<StandardResponse> send(@RequestBody EmailVerificationSendRequest req) {
        emailVerificationSendUseCase.execute(req.toCommand());
        return ResponseEntity.ok().body(StandardResponse.success());
    }

    @PostMapping("/verify")
    public ResponseEntity<StandardResponse> verify(@RequestBody EmailVerificationVerifyRequest req) {
        emailVerificationVerifyUseCase.execute(req.toCommand());
        return ResponseEntity.ok().body(StandardResponse.success());
    }
}
