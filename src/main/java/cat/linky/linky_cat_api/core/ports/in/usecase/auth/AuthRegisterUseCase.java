package cat.linky.linky_cat_api.core.ports.in.usecase.auth;

import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.auth.AuthRegisterResult;

public interface AuthRegisterUseCase {
    public AuthRegisterResult execute(AuthRegisterCommand command);
}
