package cat.linky.linky_cat_api.core.ports.in.usecase.user;

import cat.linky.linky_cat_api.core.domain.User;

public interface UserCreateUseCase {
    public User execute(User user);
}
