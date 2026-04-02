package cat.linky.linky_cat_api.adapters.in.web.controller.dto.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;

public record LinkResponse(
    UUID id,
    String title,
    String url,
    Integer sortOrder,
    Long clickCount,
    Boolean isActive
) {
    public static LinkResponse fromResult(LinkResult result) {
        return new LinkResponse(
            result.id(),
            result.title(),
            result.url(),
            result.sortOrder(),
            result.clickCount(),
            result.isActive()
        );
    }
}
