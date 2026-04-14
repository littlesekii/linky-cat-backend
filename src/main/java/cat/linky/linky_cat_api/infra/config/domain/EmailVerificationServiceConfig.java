package cat.linky.linky_cat_api.infra.config.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.linky.linky_cat_api.core.ports.out.mail.MailSenderPort;
import cat.linky.linky_cat_api.core.ports.out.repository.EmailVerificationRepositoryPort;
import cat.linky.linky_cat_api.core.service.email_verification.EmailVerificationSendService;
import cat.linky.linky_cat_api.core.service.email_verification.EmailVerificationVerifyService;
import jakarta.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class EmailVerificationServiceConfig {
    
    @Bean
    @Transactional
    public EmailVerificationSendService emailVerificationSendService(
        EmailVerificationRepositoryPort repositoryPort, 
        MailSenderPort mailSenderPort
    ) {
        return new EmailVerificationSendService(repositoryPort, mailSenderPort);
    }

    @Bean
    @Transactional
    public EmailVerificationVerifyService emailVerificationVerifyService(
        EmailVerificationRepositoryPort repositoryPort
    ) {
        return new EmailVerificationVerifyService(repositoryPort);
    }

}
