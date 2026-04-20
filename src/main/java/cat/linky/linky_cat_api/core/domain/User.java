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
            throw new InvalidArgumentException("domain.user.username.blank");
        
        if (email == null || email.isEmpty())
            throw new InvalidArgumentException("domain.user.email.blank");

        if (password == null || password.isEmpty())
            throw new InvalidArgumentException("domain.user.password.blank");

        validateUsername(username);
        validateEmail(email);
        // validatePassword(password);
    }

    public static void validateUsername(String username) {
        if (username.length() > 32) 
            throw new InvalidArgumentException("domain.user.username.max_length");

        if (username.matches(".*[^A-Za-z0-9._].*"))
            throw new InvalidArgumentException("domain.user.username.invalid_chars");

        if (!username.matches(".*[A-Za-z0-9].*"))
            throw new InvalidArgumentException("domain.user.username.required_chars");

        if (username.contains(".."))
            throw new InvalidArgumentException("domain.user.username.sequential_periods");

        if (username.startsWith(".") || username.endsWith("."))
            throw new InvalidArgumentException("domain.user.username.start_end_period");
    }

    public static void validateEmail(String email) {
        if (!email.matches("^(?=.{1,64}@)[^\\s@]+@[^\\s@]+\\.[^\\s@]{2,}$"))
            throw new InvalidArgumentException("domain.user.email.invalid");
    }

    public static void validatePassword(String password) {
        if (password.length() < 8)
            throw new InvalidArgumentException("domain.user.password.min_length");

        if (!password.matches(".*[A-Z].*")) 
            throw new InvalidArgumentException("domain.user.password.uppercase_letter");

        if (!password.matches(".*[a-z].*"))
            throw new InvalidArgumentException("domain.user.password.lowercase_letter");

        if (!password.matches(".*[0-9].*"))
            throw new InvalidArgumentException("domain.user.password.number");

        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\",./<>?].*"))
            throw new InvalidArgumentException("domain.user.password.special_char");
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
