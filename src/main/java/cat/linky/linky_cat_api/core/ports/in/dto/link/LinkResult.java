package cat.linky.linky_cat_api.core.ports.in.dto.link;

import java.util.UUID;

public record LinkResult(
    UUID id,
    String title,
    String url,
    Integer sortOrder,
    Long clickCount,
    Boolean isActive
) {}
