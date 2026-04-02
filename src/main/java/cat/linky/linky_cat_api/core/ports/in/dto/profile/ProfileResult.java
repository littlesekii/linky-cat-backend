package cat.linky.linky_cat_api.core.ports.in.dto.profile;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.link.LinkResult;

public record ProfileResult(
    UUID id,
    String displayName,
    String bio,
    Long profileViews,
    List<LinkResult> links
) {}
