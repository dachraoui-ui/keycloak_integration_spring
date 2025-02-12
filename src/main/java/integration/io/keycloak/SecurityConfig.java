package integration.io.keycloak;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthConverter jwtAuthConverter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                //Disabling CSRF (csrf.disable()) is safe only for APIs (RESTful services), where the client is a mobile app or a frontend like React/Angular
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated());
        //anyRequest().authenticated() means:
        //Every request must come from an authenticated user (e.g., user must be logged in).
        //Unauthorized users will receive a 401 Unauthorized error.

        http
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwtConfigurer -> jwtConfigurer
                                .jwtAuthenticationConverter(jwtAuthConverter)
                        )
                );
        //jwt() is a configuration method that tells Spring Security to use JWT (JSON Web Token) as the format of the access token.
        http
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS));

        return http.build();
    }

}
