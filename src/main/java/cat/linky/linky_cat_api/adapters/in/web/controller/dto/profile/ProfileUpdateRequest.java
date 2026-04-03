package cat.linky.linky_cat_api.adapters.in.web.controller.dto.profile;

import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileUpdateCommand;

public record ProfileUpdateRequest(
    String displayName,
    String bio
) {
    public ProfileUpdateCommand toCommand() {
        return new ProfileUpdateCommand(displayName, bio);
    }
}
