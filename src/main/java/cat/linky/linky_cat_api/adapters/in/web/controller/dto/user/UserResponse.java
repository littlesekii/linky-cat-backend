package cat.linky.linky_cat_api.adapters.in.web.controller.dto.user;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkResponse;
import cat.linky.linky_cat_api.core.domain.User;

// @JsonInclude(JsonInclude.Include.NON_NULL)
public record UserResponse(
    UUID id, 
    String username, 
    String email, 
    String password, 
    String displayName, 
    String bio,
    List<LinkResponse> links,
    Long profileViews
) {
    public static UserResponse fromDomain(User user) {
        return new UserResponse(
            user.getId(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword(), 
            user.getDisplayName(), 
            user.getBio(), 
            user.getLinks().stream()
                .map(link -> LinkResponse.fromDomain(link, user.getId()))
                .toList(), 
            user.getProfileViews()
        );
    }
}

