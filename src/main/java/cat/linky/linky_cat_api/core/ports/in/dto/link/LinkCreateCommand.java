package cat.linky.linky_cat_api.core.ports.in.dto.link;

public record LinkCreateCommand(
    String title,
    String url,
    Integer sortOrder,
    Boolean isActive
) {}
