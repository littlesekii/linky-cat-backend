package cat.linky.linky_cat_api.core.ports.in.dto.user;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;

public record UserResult(
    UUID id,
    String username,
    String email,
    ProfileResult profile
) {}
