package cat.linky.linky_cat_api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;
import cat.linky.linky_cat_api.core.service.link.LinkCreateService;
import cat.linky.linky_cat_api.core.service.link.LinkDeleteService;
import cat.linky.linky_cat_api.core.service.link.LinkUpdateService;
import jakarta.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class LinkBeanConfiguration {
    
    @Bean
    @Transactional
    public LinkCreateService linkCreateService(UserRepositoryPort userRepositoryPort) {
        return new LinkCreateService(userRepositoryPort);
    }

    @Bean
    @Transactional
    public LinkUpdateService linkUpdateService(UserRepositoryPort userRepositoryPort) {
        return new LinkUpdateService(userRepositoryPort);
    }

    @Bean
    @Transactional
    public LinkDeleteService linkDeleteService(UserRepositoryPort userRepositoryPort) {
        return new LinkDeleteService(userRepositoryPort);
    }
}