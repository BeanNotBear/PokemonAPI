package org.pokemon.pokemonapi.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // disables Cross-Site Request Forgery (CSRF) protection
        http.csrf(customizer -> customizer.disable());

        // all request must be authenticated
        // http.authorizeHttpRequests(request -> request.anyRequest().authenticated());

        // allow request to GET method at /api/pokemon/**
        http.authorizeHttpRequests(request ->
        {
            request.requestMatchers(HttpMethod.GET, "/api/pokemon/**").permitAll();
        });

        // all request to /api/pokemon/** must be authenticated
        http.authorizeHttpRequests(request ->
        {
            request.anyRequest().authenticated();
        });

        // request to login form is allowed
        http.formLogin(Customizer.withDefaults());

        // allow authentication basic
        // using postman with auth basic
        http.httpBasic(Customizer.withDefaults());

//        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

}
