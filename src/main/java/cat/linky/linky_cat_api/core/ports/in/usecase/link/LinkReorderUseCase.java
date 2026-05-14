package cat.linky.linky_cat_api.core.ports.in.usecase.link;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkReorderCommand;

public interface LinkReorderUseCase {
    public void execute(List<LinkReorderCommand> commandList, UUID userId);
}
