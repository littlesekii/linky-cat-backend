package cat.linky.linky_cat_api.adapters.out.persistence.jpa.user;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.User;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_user")
public class UserJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String username;
    private String email;
    private String password;

    public UserJpaEntity() {}
    public UserJpaEntity(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public UUID getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public User toDomain() {
        return new User(
            id, 
            username, 
            email, 
            password
        );
    }

    public static UserJpaEntity fromDomain(User user) {
        return new UserJpaEntity(
            user.getId(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword()
        );
    }
}
