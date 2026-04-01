package cat.linky.linky_cat_api.core.ports.out.repository;

import java.util.Optional;
import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.User;

public interface UserRepositoryPort {
    public User save(User user);

    public Optional<User> findById(UUID id);

    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
