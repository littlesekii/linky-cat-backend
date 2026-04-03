package cat.linky.linky_cat_api.adapters.out.persistence.jpa.link;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LinkJpaRepository extends JpaRepository<LinkJpaEntity, UUID> {
    public List<LinkJpaEntity> findAllByProfileId(UUID profileId);

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM t_link l
            INNER JOIN t_profile p ON l.profile_id = p.id
            WHERE
                l.id = :id AND
                p.user_id = :userId
        )
    """, nativeQuery = true)
    public boolean checkOwnership(UUID id, UUID userId);
}
