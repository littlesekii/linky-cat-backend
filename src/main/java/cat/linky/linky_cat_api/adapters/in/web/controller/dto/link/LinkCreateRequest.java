package cat.linky.linky_cat_api.adapters.in.web.controller.dto.link;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkCreateCommand;

public record LinkCreateRequest(
    String title,
    String url,
    Integer sortOrder,
    Boolean isActive
) {
    public LinkCreateCommand toCommand() {
        return new LinkCreateCommand(
            title,
            url,
            sortOrder,
            isActive
        );
    }
}
