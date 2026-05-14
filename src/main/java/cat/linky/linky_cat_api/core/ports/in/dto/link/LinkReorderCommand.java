package cat.linky.linky_cat_api.core.ports.in.dto.link;

import java.util.UUID;

public record LinkReorderCommand(
    UUID id, 
    Integer sortOrder
) {}
