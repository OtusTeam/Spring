package ru.otus.resourceserver.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "app.oauth2")
public class OAuthProps {
    private String clientId;
    private String clientSecret;
    private String signingKey;
}
