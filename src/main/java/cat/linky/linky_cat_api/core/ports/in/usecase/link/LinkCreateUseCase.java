package cat.linky.linky_cat_api.core.ports.in.usecase.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkCreateCommand;
import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;

public interface LinkCreateUseCase {
    public LinkResult execute(LinkCreateCommand command, UUID userId);
}
