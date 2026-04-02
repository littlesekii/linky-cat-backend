package cat.linky.linky_cat_api.core.ports.in.dto.auth;

import cat.linky.linky_cat_api.core.ports.in.dto.user.UserResult;

public record AuthRegisterResult(
    UserResult user
) {}
