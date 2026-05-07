package cat.linky.linky_cat_api.core.ports.in.usecase.link;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;

public interface LinkFindAllUseCase {
    public List<LinkResult> execute(UUID userId);
}
