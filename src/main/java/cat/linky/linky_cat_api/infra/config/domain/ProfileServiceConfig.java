package cat.linky.linky_cat_api.infra.config.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.service.profile.ProfileFetchByUsernameService;
import cat.linky.linky_cat_api.core.service.profile.ProfileUpdateService;
import jakarta.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class ProfileServiceConfig {
    
    @Bean
    public ProfileFetchByUsernameService profileFetchByUsernameService(
        UserRepositoryPort userRepositoryPort,
        ProfileRepositoryPort repositoryPort,
        LinkRepositoryPort linkRepositoryPort
    ) {
        return new ProfileFetchByUsernameService(userRepositoryPort, repositoryPort, linkRepositoryPort);
    }

    @Bean
    @Transactional
    public ProfileUpdateService profileUpdateService(
        ProfileRepositoryPort repositoryPort
    ) {
        return new ProfileUpdateService(repositoryPort);
    }
}
