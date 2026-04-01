package cat.linky.linky_cat_api.core.service.user;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.notFound.UserNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.dto.UserPartialUpdateDTO;
import cat.linky.linky_cat_api.core.ports.in.usecase.user.UserPartialUpdateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class UserPartialUpdateService implements UserPartialUpdateUseCase {

    private final UserRepositoryPort repositoryPort;

    public UserPartialUpdateService(UserRepositoryPort repositoryPort) {
        this.repositoryPort = repositoryPort;
    }

    @Override
    public User execute(UUID id, UserPartialUpdateDTO updateDTO) {
        User existingUser = repositoryPort.findById(id)
            .orElseThrow(() -> new UserNotFoundException());

        existingUser.updateProfile(updateDTO.displayName(), updateDTO.bio());

        return repositoryPort.save(existingUser);
    }
    
}
