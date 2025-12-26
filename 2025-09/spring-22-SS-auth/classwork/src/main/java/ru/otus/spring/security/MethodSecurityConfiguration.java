package ru.otus.spring.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@EnableGlobalMethodSecurity(
    securedEnabled = true,
    prePostEnabled = true
)
@Configuration
public class MethodSecurityConfiguration {
}
