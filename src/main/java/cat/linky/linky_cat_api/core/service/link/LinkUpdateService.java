package cat.linky.linky_cat_api.core.service.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.exception.notFound.LinkNotFoundException;
import cat.linky.linky_cat_api.core.exception.notFound.UserNotFoundException;
import cat.linky.linky_cat_api.core.ports.in.usecase.link.LinkUpdateUseCase;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

public class LinkUpdateService implements LinkUpdateUseCase {

    private final UserRepositoryPort userRepositoryPort;

    public LinkUpdateService(UserRepositoryPort userRepositoryPort) {
        this.userRepositoryPort = userRepositoryPort;
    }

    @Override
    public User execute(UUID userId, UUID id, Link link) {  
        User existingUser = userRepositoryPort.findById(userId)
            .orElseThrow(() -> new UserNotFoundException());

        Link existingLink = existingUser.getLinks().stream()
            .filter(item -> item.getId().equals(id))
            .findFirst()
            .orElseThrow(() -> new LinkNotFoundException());

        existingLink.update(link);

        return userRepositoryPort.save(existingUser);
    }
    
}
