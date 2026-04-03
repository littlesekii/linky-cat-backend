package cat.linky.linky_cat_api.core.ports.in.usecase.auth;

import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthLoginCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthLoginResult;

public interface AuthLoginUseCase {
    public AuthLoginResult execute(AuthLoginCommand command);
}
