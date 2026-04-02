package cat.linky.linky_cat_api.adapters.out.persistence.jpa.link;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkJpaRepository extends JpaRepository<LinkJpaEntity, UUID> {
    public List<LinkJpaEntity> findAllByProfileId(UUID profileId);
}
