package cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth;

import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthLoginResult;

public record AuthLoginResponse(
    String username,
    String token
) {
   public static AuthLoginResponse fromResult(AuthLoginResult result) {
        return new AuthLoginResponse(
            result.username(),
            result.token()
        );
    }
} 
