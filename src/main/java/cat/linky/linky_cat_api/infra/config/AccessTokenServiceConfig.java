package cat.linky.linky_cat_api.infra.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import cat.linky.linky_cat_api.adapters.out.security.JwtAdapter;
import cat.linky.linky_cat_api.core.ports.out.security.AccessTokenPort;

@Configuration
public class AccessTokenServiceConfig {
    
    @Bean
    public AccessTokenPort accessTokenPort(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration}") long expiration,
        @Value("${jwt.issuer}") String issuer
    ) {
        return new JwtAdapter(secret, expiration, issuer);
    }

}
