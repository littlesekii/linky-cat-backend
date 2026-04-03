package cat.linky.linky_cat_api.adapters.out.persistence.jpa.profile;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;



public interface ProfileJpaRepository extends JpaRepository<ProfileJpaEntity, UUID> {
    public Optional<ProfileJpaEntity> findByUserId(UUID userId);

    @Query(value = """
        SELECT EXISTS (
            SELECT 1
            FROM t_profile 
            WHERE
                user_id = :userId
        )
    """, nativeQuery = true)
    public boolean checkOwnership(UUID id, UUID userId);
}
