package cat.linky.linky_cat_api.core.ports.out.security;

import java.util.Map;

public interface AccessTokenPort {
    public String generateToken(String subject);
    public String generateTokenWithClaims(String subject, Map<String, String> claimMap);

    public boolean validateToken(String token);

    public String extractSubject(String token);
    public String extractClaim(String token, String claim);
}
