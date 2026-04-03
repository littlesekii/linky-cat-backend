package cat.linky.linky_cat_api.core.ports.in.dto.auth;

public record AuthLoginCommand(
    String username,
    String password
) {}
    
