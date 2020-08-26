package ru.otus.authorizationserver.services;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import ru.otus.authorizationserver.model.CustomUser;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("deprecation")
public class CustomTokenEnhancer implements TokenEnhancer {
    public static final String KEY_FIRST_NAME = "firstName";
    public static final String KEY_FATHER_NAME = "fatherName";

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        CustomUser user = (CustomUser) authentication.getPrincipal();
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(Map.of(
                KEY_FIRST_NAME, user.getFirstName(),
                KEY_FATHER_NAME, user.getFatherName()
        ));
        return accessToken;
    }
}
