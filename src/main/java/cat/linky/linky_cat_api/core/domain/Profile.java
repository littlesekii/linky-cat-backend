package cat.linky.linky_cat_api.core.domain;

import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;

public class Profile {

    private UUID id;
    private UUID userId;

    private String displayName;
    private String bio;
    
    private Long profileViews;
    
    public Profile() {}
    public Profile(UUID id, UUID userId, String displayName, String bio, Long profileViews) {
        this.id = id;
        this.userId = userId;
        this.displayName = displayName;
        this.bio = bio;
        this.profileViews = profileViews;
        validate();
    }
    public Profile(UUID userId, String displayName, String bio) {
        this.id = null;
        this.userId = userId;
        this.displayName = displayName;
        this.bio = bio;
        this.profileViews = 0L;
        validate();
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

    public void updateDisplayName(String displayName) {
        if (displayName != null) {
            if(displayName.isBlank())
                throw new InvalidArgumentException("displayName cannot be blank");

            this.displayName = displayName;
        }
    }

    public void updateBio(String bio) {
        if (bio != null) 
            this.bio = bio;
    }

    public void validate() {
        if (userId == null)
            throw new InvalidArgumentException("userId cannot be null");

        if (displayName == null || displayName.isEmpty())
            throw new InvalidArgumentException("displayName cannot be blank");

        if (profileViews < 0)
            throw new InvalidArgumentException("profileViews cannot be less than 0");  
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Profile other = (Profile) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    
}
