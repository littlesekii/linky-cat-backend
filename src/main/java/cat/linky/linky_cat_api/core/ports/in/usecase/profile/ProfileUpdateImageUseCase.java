package cat.linky.linky_cat_api.core.ports.in.usecase.profile;

import java.util.UUID;

import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileResult;
import cat.linky.linky_cat_api.core.ports.in.dto.profile.ProfileUpdateImageCommand;

public interface ProfileUpdateImageUseCase {
    public ProfileResult execute(UUID id, ProfileUpdateImageCommand command, UUID userId);
}
