package ru.otus.authorizationserver.services;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.authorizationserver.model.CustomUser;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class InMemoryUserDetailsService implements UserDetailsService {

    private final Map<String, CustomUser> userMap;

    public InMemoryUserDetailsService(PasswordEncoder passwordEncoder) {
        userMap = Map.of(
                "user1", new CustomUser("Василий", "Григорьевич",
                        "user1",
                        passwordEncoder.encode("user1"),
                        List.of(new SimpleGrantedAuthority("ADMIN"))),

                "user2", new CustomUser("Ираклий", "Спиридонович",
                        "user2",
                        passwordEncoder.encode("user2"),
                        List.of(new SimpleGrantedAuthority("USER")))
        );
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       return Optional.ofNullable(userMap.get(username)).map(CustomUser::new)
               .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", username)));
    }
}
