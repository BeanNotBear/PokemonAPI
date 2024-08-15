package org.pokemon.pokemonapi.api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private CustomUsersDetailService customUsersDetailService;

    @Autowired
    public SecurityConfig(CustomUsersDetailService customUsersDetailService) {
        this.customUsersDetailService = customUsersDetailService;
    }

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
            request.requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll();
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

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManage(
            AuthenticationConfiguration authenticationConfiguration
    ) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
