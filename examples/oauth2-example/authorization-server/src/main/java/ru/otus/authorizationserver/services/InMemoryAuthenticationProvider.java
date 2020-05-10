package ru.otus.authorizationserver.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.otus.authorizationserver.model.CustomUser;

import java.util.Map;

@RequiredArgsConstructor
public class InMemoryAuthenticationProvider implements AuthenticationProvider {

    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_FATHER_NAME = "fatherName";
    private final UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        if (authentication.getName() == null || authentication.getCredentials() == null) {
            return null;
        }

        if (authentication.getName().isEmpty() || authentication.getCredentials().toString().isEmpty()) {
            return null;
        }

        final String userName = authentication.getName();
        final Object password = authentication.getCredentials();

        CustomUser userDetails = (CustomUser) userDetailsService.loadUserByUsername(userName);


        if (userName.equalsIgnoreCase(userDetails.getUsername()) && password.equals(userDetails.getPassword())) {
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    userDetails.getUsername(),
                    userDetails.getPassword(),
                    userDetails.getAuthorities());

            token.setDetails(
                    Map.of(
                            KEY_FIRST_NAME, userDetails.getFirstName(),
                            KEY_FATHER_NAME, userDetails.getFatherName()
                    )
            );
            return token;
        }

        throw new UsernameNotFoundException("Invalid username or password.");
    }

    @Override
    public boolean supports(final Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
