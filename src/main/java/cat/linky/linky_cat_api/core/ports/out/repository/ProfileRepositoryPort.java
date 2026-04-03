package cat.linky.linky_cat_api.core.ports.out.repository;

import java.util.Optional;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Profile;

public interface ProfileRepositoryPort {
    public Optional<Profile> findById(UUID id);
    public Optional<Profile> findByUserId(UUID userId);

    public Profile save(Profile profile);
    public boolean checkOwnership(UUID id, UUID userId);
}
