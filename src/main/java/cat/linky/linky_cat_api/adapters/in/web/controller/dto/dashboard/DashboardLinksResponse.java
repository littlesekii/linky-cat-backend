package cat.linky.linky_cat_api.adapters.in.web.controller.dto.dashboard;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.adapters.in.web.controller.dto.link.LinkResponse;
import cat.linky.linky_cat_api.core.ports.in.dto.dashboard.DashboardLinksResult;

public record DashboardLinksResponse(
    UUID profileId,
    String displayName,
    String bio,
    List<LinkResponse> links
) {
    public static DashboardLinksResponse fromResult(DashboardLinksResult result) {
        return new DashboardLinksResponse(
            result.profileId(),
            result.displayName(), 
            result.bio(), 
            result.links().stream()
                .map(LinkResponse::fromResult)
                .toList()
        );
    }

}
