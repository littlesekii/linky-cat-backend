package cat.linky.linky_cat_api.adapters.in.web.controller.dto.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkReorderCommand;

public record LinkReorderRequest(
    UUID id, 
    Integer sortOrder
) {
    public LinkReorderCommand toCommand() {
        return new LinkReorderCommand(
            id,
            sortOrder
        );
    }
}
