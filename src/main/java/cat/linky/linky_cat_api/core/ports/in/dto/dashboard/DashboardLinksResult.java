package cat.linky.linky_cat_api.core.ports.in.dto.dashboard;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;

public record DashboardLinksResult(
    UUID profileId,
    String displayName,
    String bio,
    List<LinkResult> links
) {}
