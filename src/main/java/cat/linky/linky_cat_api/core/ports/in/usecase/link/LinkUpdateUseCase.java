package cat.linky.linky_cat_api.core.ports.in.usecase.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkUpdateCommand;

public interface LinkUpdateUseCase {
    public LinkResult execute(UUID id, LinkUpdateCommand command, UUID userId);
}
