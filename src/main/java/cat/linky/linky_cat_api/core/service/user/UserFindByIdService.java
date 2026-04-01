package cat.linky.linky_cat_api.core.service.user;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.notFound.UserNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.usecase.user.UserFindByIdUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class UserFindByIdService implements UserFindByIdUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserFindByIdService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(UUID id) {
        return repositoryPort.findById(id)
            .orElseThrow(() -> new UserNotFoundException());
    }

    
}
