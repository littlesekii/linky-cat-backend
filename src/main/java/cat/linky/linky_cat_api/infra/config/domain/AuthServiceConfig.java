package cat.linky.linky_cat_api.infra.config.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.security.PasswordEncoderPort;
import cat.linky.linky_cat_api.core.service.auth.AuthRegisterService;
import jakarta.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class AuthServiceConfig {
    
    @Bean
    @Transactional
    public AuthRegisterService authRegisterService(
        UserRepositoryPort repositoryPort,
        ProfileRepositoryPort profileRepositoryPort,
        PasswordEncoderPort passwordEncoderPort
    ) {
        return new AuthRegisterService(repositoryPort, profileRepositoryPort, passwordEncoderPort);
    }
}