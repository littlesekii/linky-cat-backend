package cat.linky.linky_cat_api.adapters.out.persistence.jpa.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.domain.Profile;
import cat.linky.linky_cat_api.core.ports.out.repository.ProfileRepositoryPort;

@Component
public class ProfileJpaAdapter implements ProfileRepositoryPort {

    private final ProfileJpaRepository repository;
    
    public ProfileJpaAdapter(ProfileJpaRepository repository) {
        this.repository = repository;
    }
    
    @Override
    public Optional<Profile> findById(UUID id) {
        Optional<ProfileJpaEntity> entity = repository.findById(id);
        return entity.map(ProfileJpaEntity::toDomain);
    }

    @Override
    public Optional<Profile> findByUserId(UUID userId) {
        Optional<ProfileJpaEntity> entity = repository.findByUserId(userId);
        return entity.map(ProfileJpaEntity::toDomain);
    }

    @Override
    public Profile save(Profile profile) {
        ProfileJpaEntity entity = ProfileJpaEntity.fromDomain(profile);
        ProfileJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }
    
}
