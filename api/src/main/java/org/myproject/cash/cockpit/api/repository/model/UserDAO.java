package org.myproject.cash.cockpit.api.repository.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder(toBuilder = true)
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
public class UserDAO extends AbstractDAO implements UserDetails {

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    @JoinTable(name = "user_token",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "token_id"))
    private List<TokenDAO> tokens;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "isEnabled")
    private boolean enabled;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
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
}
