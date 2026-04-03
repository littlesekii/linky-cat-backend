package cat.linky.linky_cat_api.adapters.out.persistence.jpa.user;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import cat.linky.linky_cat_api.core.domain.User;
import cat.linky.linky_cat_api.core.ports.out.repository.UserRepositoryPort;

@Component
public class UserJpaAdapter implements UserRepositoryPort {

    private final UserJpaRepository repository;
    
    public UserJpaAdapter(UserJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> findById(UUID id) {
        Optional<UserJpaEntity> entity = repository.findById(id);
        return entity.map(UserJpaEntity::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Optional<UserJpaEntity> entity = repository.findByUsername(username);
        return entity.map(UserJpaEntity::toDomain);
    }
    
    @Override
    public User save(User user) {
        UserJpaEntity entity = UserJpaEntity.fromDomain(user);
        UserJpaEntity saved = repository.save(entity);
        return saved.toDomain();
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }

    @Override
    public boolean existsByEmail(String email) {
        return repository.existsByEmail(email);
    }

}
