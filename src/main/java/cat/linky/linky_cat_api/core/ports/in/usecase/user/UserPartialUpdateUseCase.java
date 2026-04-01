package cat.linky.linky_cat_api.core.ports.in.usecase.user;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.ports.in.dto.UserPartialUpdateDTO;

public interface UserPartialUpdateUseCase {
    User execute(UUID id, UserPartialUpdateDTO updateDTO);
}
