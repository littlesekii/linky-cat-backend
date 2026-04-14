package cat.linky.linky_cat_api.core.domain;

import java.util.UUID;

import cat.linky.linky_cat_api.core.exception.InvalidArgumentException;

public class User {

    private UUID id;

    private String username;
    private String email;
    private String password;

    public User() {}
    public User(UUID id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        validate();
    }
    public User(String username, String email, String password) {
        this.id = null;
        this.username = username;
        this.email = email;
        this.password = password;
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

    public void validate() {
        if (username == null || username.isEmpty())
            throw new InvalidArgumentException("username cannot be blank");
        
        if (email == null || email.isEmpty())
            throw new InvalidArgumentException("email cannot be blank");

        validateUsername(username);
    }

    public static void validateUsername(String username) {
         if (username.length() > 32) 
            throw new InvalidArgumentException("username length cannot be over 32 characters");

        if (username.matches(".*[^A-Za-z0-9._].*"))
            throw new InvalidArgumentException("username can only contain letters (A-Z, a-z), numbers, underscores, and periods");

        if (!username.matches(".*[A-Za-z0-9].*"))
            throw new InvalidArgumentException("username must contain at least one letter (A-Z, a-z) or number");

        if (username.contains(".."))
            throw new InvalidArgumentException("username cannot contain more than one period in a row");

        if (username.startsWith(".") || username.endsWith("."))
            throw new InvalidArgumentException("username cannot start or end with a period");
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
}
