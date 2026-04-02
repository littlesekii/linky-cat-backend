package cat.linky.linky_cat_api.adapters.in.web.controller.dto.auth;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.user.UserResponse;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterResult;

public record AuthRegisterResponse(
    UserResponse user
) {
    public static AuthRegisterResponse fromResult(AuthRegisterResult result) {
        return new AuthRegisterResponse(
            UserResponse.fromResult(result.user())
        );
    }
}