package cat.linky.linky_cat_api.adapters.in.web.controller.dto.link;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkUpdateCommand;

public record LinkUpdateRequest(
    String title,
    String url,
    Integer sortOrder,
    Boolean isActive
) {
    public LinkUpdateCommand toCommand() {
        return new LinkUpdateCommand(
            title,
            url,
            sortOrder,
            isActive
        );
    }
}
