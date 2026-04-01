package cat.linky.linky_cat_api.core.ports.in.usecase.user;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.User;

public interface UserFindByIdUseCase {
    public User execute(UUID id);
}
