package cat.linky.linky_cat_api.adapters.out.persistence.jpa.user;

import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.adapters.out.persistence.jpa.link.LinkJpaEntity;
import cat.linky.linky_cat_api.core.domain.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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

    private String displayName;
    private String bio;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<LinkJpaEntity> links;

    private Long profileViews;

    public UserJpaEntity() {}
    public UserJpaEntity(UUID id, String username, String email, String password, String displayName, String bio,
            List<LinkJpaEntity> links, Long profileViews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.bio = bio;
        this.links = links;
        this.profileViews = profileViews;
    }
    public UserJpaEntity(UUID id, String username, String email, String password, String displayName, String bio, Long profileViews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.bio = bio;
        this.profileViews = profileViews;
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

    public String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public List<LinkJpaEntity> getLinks() {
        return links;
    }

    public Long getProfileViews() {
        return profileViews;
    }

    private void setLinks(List<LinkJpaEntity> links) {
        this.links = links;
    }
    
    public User toDomain() {
        return new User(
            id, 
            username, 
            email, 
            password, 
            displayName, 
            bio, 
            links.stream()
                .map(LinkJpaEntity::toDomain)
                .toList(), 
            profileViews);
    }

    public static UserJpaEntity fromDomain(User user) {
        UserJpaEntity entity = new UserJpaEntity(
            user.getId(), 
            user.getUsername(), 
            user.getEmail(), 
            user.getPassword(), 
            user.getDisplayName(), 
            user.getBio(), 
            user.getProfileViews()
        );

        List<LinkJpaEntity> links = user.getLinks().stream()
            .map(link -> LinkJpaEntity.fromDomain(link, entity))
            .toList();

        entity.setLinks(links);

        return entity;
    }
    


}
