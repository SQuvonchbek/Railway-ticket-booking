package uz.pdp.railwayticketproject.security;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import uz.pdp.railwayticketproject.security.jwt.JwtFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfiguration {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-ui.html",
            "/v1/auth/**",
            "/v1/railway/**",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http, JwtFilter jwtFilter) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(register -> register
                        .requestMatchers(AUTH_WHITELIST)
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));


        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OpenAPI openAPI(){

        return new OpenAPI().addSecurityItem(new SecurityRequirement()
                .addList("BearerAuthentication"))
                .components(new Components()
                        .addSecuritySchemes("BearerAuthentication", new SecurityScheme()
                                .name("Bearer")
                                .type(SecurityScheme.Type.HTTP).bearerFormat("Jwt")
                                .in(SecurityScheme.In.HEADER)
                                .scheme("Bearer")))
                ;
    }


}
