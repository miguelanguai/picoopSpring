package project.picoop.user.model;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String email;
    private String password;
    private String role;
    private int credits;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role));
    }

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id new value of {@link #getId}.
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email value of {@link #getEmail}.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password new value of {@link #getPassword}.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role new value of {@link #getRole}.
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * 
     * @return credits
     */
    public int getCredits() {
        return this.credits;
    }

    /**
     * @param credits new value of {@link #getCredits}.
     */
    public void setCredits(int credits) {
        this.credits = credits;
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
