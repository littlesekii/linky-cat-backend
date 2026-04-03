package cat.linky.linky_cat_api.core.ports.in.dto.auth;

public record AuthLoginResult(
    String username,
    String token
) {} 
