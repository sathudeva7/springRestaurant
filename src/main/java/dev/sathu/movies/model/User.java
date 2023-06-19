package dev.sathu.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

@Document(collection = "user")
@Data
@AllArgsConstructor
public class User implements UserDetails {
    @Id
    private ObjectId id;
    private String name;
    private String email;
    private String password;
    private String phoneNumber;
    private String token;
    private Boolean isVerified = false;
    private Address address;

    private Roles roles;
    public User(String s, String password) {

    }


    public User(String name, String email, String phoneNumber, String token,String role) {
        this.name = name;
        this.token = token;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = Roles.valueOf(role);
    }

    public <T> User(String email, String password, Set<T> role_admin) {
        this.email = email;
        this.password = password;
    }

    public User() {

    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
