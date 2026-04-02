package cat.linky.linky_cat_api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.service.link.LinkCreateService;
import cat.linky.linky_cat_api.core.service.link.LinkDeleteService;
import cat.linky.linky_cat_api.core.service.link.LinkUpdateService;
import jakarta.transaction.Transactional;

@Configuration
@EnableTransactionManagement
public class LinkBeanConfiguration {
    
    @Bean
    @Transactional
    public LinkCreateService linkCreateService(
        ProfileRepositoryPort profileRepositoryPort,
        LinkRepositoryPort repositoryPort
    ) {
        return new LinkCreateService(profileRepositoryPort, repositoryPort);
    }

    @Bean
    @Transactional
    public LinkUpdateService linkUpdateService(LinkRepositoryPort repositoryPort) {
        return new LinkUpdateService(repositoryPort);
    }

    @Bean
    @Transactional
    public LinkDeleteService linkDeleteService(LinkRepositoryPort repositoryPort) {
        return new LinkDeleteService(repositoryPort);
    }
}