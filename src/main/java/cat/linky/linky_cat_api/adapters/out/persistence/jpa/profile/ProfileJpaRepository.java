package cat.linky.linky_cat_api.adapters.out.persistence.jpa.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;



public interface ProfileJpaRepository extends JpaRepository<ProfileJpaEntity, UUID> {
    public Optional<ProfileJpaEntity> findByUserId(UUID userId);
}
