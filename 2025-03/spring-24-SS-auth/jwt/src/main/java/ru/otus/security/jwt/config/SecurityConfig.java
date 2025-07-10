package ru.otus.security.jwt.config;

import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.BearerTokenAuthenticationEntryPoint;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@Configuration
public class SecurityConfig {

    @Value("${jwt.public.key}")
    RSAPublicKey key;

    @Value("${jwt.private.key}")
    RSAPrivateKey priv;

    @Bean
    public SecurityFilterChain securityFilterChain( HttpSecurity http ) throws Exception {

        http
                .authorizeHttpRequests( ( authorize ) -> authorize
                        .anyRequest().authenticated()
                )
                .csrf( AbstractHttpConfigurer::disable )
                //.httpBasic( Customizer.withDefaults() )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .sessionManagement( ( session ) -> session.sessionCreationPolicy( SessionCreationPolicy.STATELESS ) )
                .exceptionHandling( ( exceptions ) -> exceptions
                        .authenticationEntryPoint( new BearerTokenAuthenticationEntryPoint() )
                        .accessDeniedHandler( new BearerTokenAccessDeniedHandler() )
                );

        return http.build();
    }

    @Bean
    UserDetailsService users() {

        return new InMemoryUserDetailsManager(
                User.withUsername( "user" )
                        .password( "password" )
                        .authorities( "app", "admin", "manager", "the_best" )
                        .build()
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode( CharSequence charSequence ) {
                return charSequence.toString();
            }

            @Override
            public boolean matches( CharSequence charSequence, String s ) {
                return charSequence.toString().equals( s );
            }
        };
    }

    @Bean
    JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withPublicKey( this.key ).build();
    }

    @Bean
    JwtEncoder jwtEncoder() {
        JWK jwk = new RSAKey.Builder( this.key ).privateKey( this.priv ).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>( new JWKSet( jwk ) );
        return new NimbusJwtEncoder( jwks );
    }
}
