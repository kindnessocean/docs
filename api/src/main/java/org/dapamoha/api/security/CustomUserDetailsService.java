package org.dapamoha.api.security;

import java.util.ArrayList;
import java.util.Collection;
import org.dapamoha.shared.posgresql.entity.User;
import org.dapamoha.shared.posgresql.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
    private final UserRepository repository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.repository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.getUserByAddress(username);

        if (user == null) {
            throw new UsernameNotFoundException("Address: " + username + " not found");
        }

        // FIXME username is address, password is empty string
        return new org.springframework.security.core.userdetails.User(
                username,
                "",
                getGrantedAuthorities(user)
        );
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(User user) {

        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();

        user.getRoles().forEach(role -> grantedAuthority.add(new SimpleGrantedAuthority(role.getRole().getValue())));

        return grantedAuthority;
    }
}
