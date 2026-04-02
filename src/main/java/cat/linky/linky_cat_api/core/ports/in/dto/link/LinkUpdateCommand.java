package cat.linky.linky_cat_api.core.ports.in.dto.link;

public record LinkUpdateCommand(
    String title,
    String url,
    Integer sortOrder,
    Boolean isActive
) {} 
