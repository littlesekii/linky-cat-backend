package cat.linky.linky_cat_api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.service.user.UserCreateService;
import cat.linky.linky_cat_api.core.service.user.UserFindByIdService;
import cat.linky.linky_cat_api.core.service.user.UserPartialUpdateService;

@Configuration
@EnableTransactionManagement
public class UserBeanConfiguration {
    
    @Bean
    public UserFindByIdService userFindByIdService(UserRepositoryPort repositoryPort) {
        return new UserFindByIdService(repositoryPort);
    }

    @Bean
    @Transactional
    public UserCreateService userCreateService(UserRepositoryPort repositoryPort) {
        return new UserCreateService(repositoryPort);
    }

    @Bean
    @Transactional
    public UserPartialUpdateService userPartialUpdateService(UserRepositoryPort repositoryPort) {
        return new UserPartialUpdateService(repositoryPort);
    }
}
