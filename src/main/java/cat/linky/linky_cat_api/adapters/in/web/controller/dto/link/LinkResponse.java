package cat.linky.linky_cat_api.adapters.in.web.controller.dto.link;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Link;

public record LinkResponse(
    UUID id,
    UUID userId,
    String title,
    String url,
    Integer sortOrder,
    Long clickCount,
    Boolean isActive
) {
    public static LinkResponse fromDomain(Link link, UUID userId) {
        return new LinkResponse(
            link.getId(),
            userId,
            link.getTitle(),
            link.getUrl(),
            link.getsortOrder(),
            link.getClickCount(),
            link.getIsActive()
        );
    }
}
