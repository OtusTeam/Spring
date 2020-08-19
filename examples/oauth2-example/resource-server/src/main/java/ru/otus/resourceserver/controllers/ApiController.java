package ru.otus.resourceserver.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;
import ru.otus.resourceserver.models.CurrentUserRequestResult;

import java.util.Map;

@SuppressWarnings("deprecation")
@RequiredArgsConstructor
@RestController
public class ApiController {

    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_FATHER_NAME = "fatherName";

    private final TokenStore tokenStore;

    @GetMapping("api/current-user")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('ADMIN')")
    public CurrentUserRequestResult getCurrentUser(Authentication authentication) {

        Map<String, Object> info = getAdditionalInfo(authentication);
        return new CurrentUserRequestResult((String) authentication.getPrincipal(),
                (String) info.get(KEY_FIRST_NAME),
                (String) info.get(KEY_FATHER_NAME));
    }

    private Map<String, Object> getAdditionalInfo(Authentication authentication) {
        OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
        OAuth2AccessToken accessToken = tokenStore.readAccessToken(details.getTokenValue());
        return accessToken.getAdditionalInformation();
    }
}
