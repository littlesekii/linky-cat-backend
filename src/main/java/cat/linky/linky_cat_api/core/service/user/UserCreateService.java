package cat.linky.linky_cat_api.core.service.user;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.IntegrityViolationException;
import cat.linky.linky_cat_api.core.ports.in.usecase.user.UserCreateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class UserCreateService implements UserCreateUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserCreateService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(User user) {
        if (repositoryPort.existsByUsername(user.getUsername()))
            throw new IntegrityViolationException("this username is already taken");

        if (repositoryPort.existsByEmail(user.getEmail()))
            throw new IntegrityViolationException("this email is already registered");

        return repositoryPort.save(user);
    }
}
