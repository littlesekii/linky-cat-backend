package cat.linky.linky_cat_api.adapters.out.persistence.jpa.profile;

import java.util.UUID;

import cat.linky.linky_cat_api.core.domain.Profile;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_profile")
public class ProfileJpaEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    UUID id;

    @Column(name = "userId", nullable = false)
    UUID userId;

    private String displayName;
    private String bio;

    private Long profileViews;
    
    public ProfileJpaEntity() {}
    public ProfileJpaEntity(UUID id, UUID userId, String displayName, String bio, Long profileViews) {
        this.id = id;
        this.userId = userId;
        this.displayName = displayName;
        this.bio = bio;
        this.profileViews = profileViews;
    }

    public UUID getId() {
        return id;
    }

    public UUID getUserId() {
        return userId;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getBio() {
        return bio;
    }

    public Long getProfileViews() {
        return profileViews;
    }

    public Profile toDomain() {
        return new Profile(
            id, 
            userId,
            displayName, 
            bio, 
            profileViews
        );
    }
    
    public static ProfileJpaEntity fromDomain(Profile profile) {
        return new ProfileJpaEntity(
            profile.getId(), 
            profile.getUserId(),
            profile.getDisplayName(), 
            profile.getBio(),
            profile.getProfileViews()
        );
    }
}
