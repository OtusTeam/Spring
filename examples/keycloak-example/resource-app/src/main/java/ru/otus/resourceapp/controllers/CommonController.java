package ru.otus.resourceapp.controllers;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CommonController {

    @GetMapping(path = "/")
    public String commonData() {
        return "Абсолютно свободные данные";
    }

    @SuppressWarnings("rawtypes")
    @GetMapping(path = "/secret")
    public String secretData(Principal principal) {
        var authenticationToken = (KeycloakAuthenticationToken) principal;
        var kp = (KeycloakPrincipal) authenticationToken.getPrincipal();
        var token = kp.getKeycloakSecurityContext().getToken();

        return "Жутко секретные данные для пользователя: " + token.getPreferredUsername() +
                ", \n остальные данные: " + token.getOtherClaims();
    }
}
