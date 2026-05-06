package cat.linky.linky_cat_api.infra.config.domain;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cat.linky.linky_cat_api.core.ports.out.repository.LinkRepositoryPort;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;
import cat.linky.linky_cat_api.core.service.dashboard.DashboardLinksFetchService;

@Configuration
public class DashboardServiceConfig {

    @Bean
    public DashboardLinksFetchService dashboardLinksFetchService(
        ProfileRepositoryPort profileRepositoryPort,
        LinkRepositoryPort linkRepositoryPort
    ) {
        return new DashboardLinksFetchService(profileRepositoryPort, linkRepositoryPort);
    }
}
