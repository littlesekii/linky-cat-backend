package cat.linky.linky_cat_api.core.ports.in.dto.auth;

public record AuthRegisterCommand(
    String username, 
    String email, 
    String password, 
    String displayName, 
    String bio
) {}
