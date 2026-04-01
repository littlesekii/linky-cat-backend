package cat.linky.linky_cat_api.adapters.out.persistence.jpa.user;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<UserJpaEntity, UUID> {
    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
