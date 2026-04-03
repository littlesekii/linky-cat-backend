package cat.linky.linky_cat_api.adapters.out.security;

import java.time.Instant;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

import cat.linky.linky_cat_api.core.ports.out.security.AccessTokenPort;

public class JwtAdapter implements AccessTokenPort {
    
    private final String secret;
    private final long expiration;
    private final String issuer;
    private final Algorithm algorithm;
    private final JWTVerifier verifier;

    public JwtAdapter(
        @Value("${jwt.secret}") String secret,
        @Value("${jwt.expiration}") long expiration,
        @Value("${jwt.issuer}") String issuer
    ) {
        this.secret = secret;
        this.expiration = expiration;
        this.issuer = issuer;
        this.algorithm = Algorithm.HMAC256(this.secret);
        verifier = JWT.require(algorithm)
            .withIssuer(issuer)
            .build();
    }

    @Override
    public String generateToken(String subject) {
        return JWT.create().withIssuer(issuer)
            .withSubject(subject)
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plusSeconds(expiration))
            .sign(algorithm);
    }

    @Override
    public String generateTokenWithClaims(String subject, Map<String, String> claimMap) {
        Builder jwtBuilder = JWT.create().withIssuer(issuer)
            .withSubject(subject)
            .withIssuedAt(Instant.now())
            .withExpiresAt(Instant.now().plusSeconds(expiration));

        claimMap.forEach((k, v) -> jwtBuilder.withClaim(k, v));
        
        return jwtBuilder.sign(algorithm);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            return false;
        }
    }

    @Override
    public String extractSubject(String token) {
        return verifier.verify(token).getSubject();
    }

    @Override
    public String extractClaim(String token, String claim) {
        return verifier.verify(token).getClaim(claim).asString();
    }  
}
