package cat.linky.linky_cat_api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.service.profile.ProfileFetchByUsernameService;

@Configuration
public class ProfileBeanConfig {
    
    @Bean
    public ProfileFetchByUsernameService profileFetchByUsernameService(
        UserRepositoryPort userRepositoryPort,
        ProfileRepositoryPort repositoryPort,
        LinkRepositoryPort linkRepositoryPort
    ) {
        return new ProfileFetchByUsernameService(userRepositoryPort, repositoryPort, linkRepositoryPort);
    }
}
