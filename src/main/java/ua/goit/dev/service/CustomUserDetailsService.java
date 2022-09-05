package ua.goit.dev.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.dev.model.dao.User;
import ua.goit.dev.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = repository.findByEmail(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("user with email %s not exists", username)));
        return new MyUserDetails(user);
    }

    public static class MyUserDetails implements UserDetails {
        private User user;


        public MyUserDetails(User user) {
            this.user = user;

        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
             return List.of(new SimpleGrantedAuthority(user.getRole().getName()));

        }

        @Override
        public String getPassword() {
            return user.getPassword();
        }

        @Override
        public String getUsername() {
            return this.user.getEmail();
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
}
