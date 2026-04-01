package cat.linky.linky_cat_api.adapters.in.web.controller.dto.link;

import cat.linky.linky_cat_api.core.domain.Link;

public record LinkCreateRequest(
    String title, 
    String url, 
    Integer sortOrder, 
    Boolean isActive
) {
    public Link toDomain() {
        return new Link(
            title, 
            url, 
            sortOrder, 
            isActive
        );
    }
}
