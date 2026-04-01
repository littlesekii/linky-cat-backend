package cat.linky.linky_cat_api.core.ports.in.usecase.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;
import cat.linky.linky_cat_api.core.domain.User;

public interface LinkUpdateUseCase {
    public User execute(UUID userId, UUID id, Link link);
}
