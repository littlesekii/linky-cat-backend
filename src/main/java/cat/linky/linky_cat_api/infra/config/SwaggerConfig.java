package cat.linky.linky_cat_api.infra.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    
    @Bean
    public OpenAPI appOpenAPI() {
        return new OpenAPI().info(
            new Info().title("Linky Cat API")
            .description("This is the official Linky Cat API")
            .version("v1.0.0")
        );
    }
}
