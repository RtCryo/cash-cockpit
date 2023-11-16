package org.myproject.cash.cockpit.api.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.myproject.cash.cockpit.api.repository.UserRepository;
import org.myproject.cash.cockpit.api.repository.model.UserDAO;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;

@Service("myUserDetailsService")
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        try {
            UserDAO userModel = userRepository.findByUsername(username);
            return SecurityUserModel.fromUser(userModel);
        } catch (Exception ex) {
            throw new UsernameNotFoundException("User doesn't exists");
        }
    }

    @Getter
    @RequiredArgsConstructor
    private class SecurityUserModel implements UserDetails {

        private final String username;
        private final String password;
        private final boolean isActive;

        public static UserDetails fromUser(UserDAO user) {
            return new org.springframework.security.core.userdetails.User(
                    user.getUsername(), user.getPassword(), Collections.emptyList()
            );
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return Collections.emptyList();
        }

        @Override
        public boolean isAccountNonExpired() {
            return isActive;
        }

        @Override
        public boolean isAccountNonLocked() {
            return isActive;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return isActive;
        }

        @Override
        public boolean isEnabled() {
            return isActive;
        }
    }
}
