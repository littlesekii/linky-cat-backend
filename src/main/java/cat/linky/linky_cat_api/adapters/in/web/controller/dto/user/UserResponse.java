package cat.linky.linky_cat_api.adapters.in.web.controller.dto.user;

import java.util.UUID;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile.ProfileResponse;
import cat.linky.linky_cat_api.core.ports.in.dto.user.UserResult;

public record UserResponse (
    UUID id,
    String username,
    String email,
    ProfileResponse profile
) {
    public static UserResponse fromResult(UserResult result) {
        return new UserResponse(
            result.id(),
            result.username(),
            result.email(),
            ProfileResponse.fromResult(result.profile())
        );
    }
}
