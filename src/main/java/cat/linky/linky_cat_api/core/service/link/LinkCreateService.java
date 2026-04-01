package cat.linky.linky_cat_api.core.service.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.notFound.UserNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkCreateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class LinkCreateService implements LinkCreateUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public LinkCreateService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(UUID userId, Link link) {
        User existingUser = userRepositoryPort.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        existingUser.addLink(link);

        return userRepositoryPort.save(existingUser);
    }
    
}
