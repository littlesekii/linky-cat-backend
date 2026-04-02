package cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth;

import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterCommand;

public record AuthRegisterRequest(
    String username, 
    String email, 
    String password, 
    String passwordConfirmation,
    String displayName, 
    String bio
) {
    public AuthRegisterCommand toCommand() {
        return new AuthRegisterCommand(
            username,
            email,
            password,
            passwordConfirmation,
            displayName,
            bio
        );
    }
}
