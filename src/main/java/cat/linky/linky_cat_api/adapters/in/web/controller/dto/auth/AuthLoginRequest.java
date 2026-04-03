package cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth;

import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthLoginCommand;

public record AuthLoginRequest(
    String username,
    String password
) {
    public AuthLoginCommand toCommand() {
        return new AuthLoginCommand(
            username,
            password
        );
    }
}
    
