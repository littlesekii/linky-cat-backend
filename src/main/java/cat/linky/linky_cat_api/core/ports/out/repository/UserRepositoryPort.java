package cat.linky.linky_cat_api.core.ports.out.repository;

import java.util.Optional;

import cat.linky.linky_cat_api.core.domain.User;

public interface UserRepositoryPort {
    Optional<User> findByUsername(String username);

    public User save(User user);

    public boolean existsByUsername(String username);
    public boolean existsByEmail(String email);
}
