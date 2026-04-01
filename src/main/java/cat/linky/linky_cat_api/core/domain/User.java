package cat.linky.linky_cat_api.core.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;

public class User {

    private UUID id;

    private String username;
    private String email;
    private String password;

    private String displayName;
    private String bio;
    private List<Link> links;

    private Long profileViews;

    public User() {}
    public User(UUID id, String username, String email, String password, String displayName, String bio,
            List<Link> links, Long profileViews) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.bio = bio;
        this.links = new ArrayList<>(links);
        this.profileViews = profileViews;
        validate();
    }
    public User(String username, String email, String password, String displayName, String bio) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.password = password;
        this.displayName = displayName;
        this.bio = bio;
        this.links = new ArrayList<>();
        this.profileViews = 0L;
        validate();
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

    public List<Link> getLinks() {
        return links;
    }

    public Long getProfileViews() {
        return profileViews;
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
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }    

    public void updateProfile(String displayName, String bio) {

        if (displayName != null && displayName.isBlank())
            throw new InvalidArgumentException("displayName cannot be blank");

        if (displayName != null) {
            this.displayName = displayName;
        }
        if (bio != null) {
            this.bio = bio;
        }
    }

    public void addLink(Link link) {
        links.add(link);
    }
    public void removeLink(Link link) {
        links.remove(link);
    }

    public void validate() {
        if (username == null || username.isEmpty())
            throw new InvalidArgumentException("username cannot be blank");
        
        if (email == null || email.isEmpty())
            throw new InvalidArgumentException("email cannot be blank");

        if (displayName == null || displayName.isEmpty())
            throw new InvalidArgumentException("displayName cannot be blank");

        if (links == null)
            throw new InvalidArgumentException("link list cannot be null");

        if (profileViews < 0)
            throw new InvalidArgumentException("profileViews cannot be less than 0");  
    }
}
