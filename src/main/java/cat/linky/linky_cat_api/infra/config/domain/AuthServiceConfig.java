package cat.linky.linky_cat_api.infra.config.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.linky.linky_cat_api.core.ports.out.repository.EmailVerificationRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.security.AccessTokenPort;
import cat.linky.linky_cat_api.core.ports.out.security.PasswordEncoderPort;
import cat.linky.linky_cat_api.core.service.auth.AuthCheckEmailService;
import cat.linky.linky_cat_api.core.service.auth.AuthCheckUsernameService;
import cat.linky.linky_cat_api.core.service.auth.AuthLoginService;
import cat.linky.linky_cat_api.core.service.auth.AuthRegisterService;
import jakarta.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class AuthServiceConfig {
    
    @Bean
    @Transactional
    public AuthRegisterService authRegisterService(
        UserRepositoryPort userRepositoryPort,
        ProfileRepositoryPort profileRepositoryPort,
        EmailVerificationRepositoryPort emailVerificationRepositoryPort,
        PasswordEncoderPort passwordEncoderPort
    ) {
        return new AuthRegisterService(userRepositoryPort, profileRepositoryPort, emailVerificationRepositoryPort, passwordEncoderPort);
    }

    @Bean
    public AuthLoginService authLoginService(
        UserRepositoryPort userRepositoryPort,
        AccessTokenPort accessTokenPort, 
        PasswordEncoderPort passwordEncoderPort
    ) {
        return new AuthLoginService(userRepositoryPort, accessTokenPort, passwordEncoderPort);
    }

    @Bean
    public AuthCheckEmailService authCheckEmailService(
        UserRepositoryPort userRepositoryPort
    ) {
        return new AuthCheckEmailService(userRepositoryPort);
    }

    @Bean
    public AuthCheckUsernameService authCheckUsernameService(
        UserRepositoryPort userRepositoryPort
    ) {
        return new AuthCheckUsernameService(userRepositoryPort);
    }
}