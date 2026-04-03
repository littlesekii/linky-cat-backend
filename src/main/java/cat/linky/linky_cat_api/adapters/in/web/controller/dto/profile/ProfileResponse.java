package cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkResponse;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProfileResponse(
    UUID id,
    String displayName,
    String bio,
    Long profileViews,
    List<LinkResponse> links
) {
    public static ProfileResponse fromResult(ProfileResult result) {
        return new ProfileResponse(
            result.id(),
            result.displayName(),
            result.bio(),
            result.profileViews(),
            Optional.ofNullable(result.links())
                .map(links -> links.stream().map(LinkResponse::fromResult).toList())
                .orElse(null)
        );
    }
}
