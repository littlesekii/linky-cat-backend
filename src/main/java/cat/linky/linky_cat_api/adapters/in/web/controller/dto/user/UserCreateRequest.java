package cat.linky.linky_cat_api.adapters.in.web.controller.dto.user;

import cat.linky.linky_cat_api.core.domain.User;

public record UserCreateRequest(
    String username, 
    String email, 
    String password, 
    String displayName, 
    String bio
) {
    public User toDomain() {
        return new User(
            username,
            email,
            password,
            displayName,
            bio
        );
    }
}