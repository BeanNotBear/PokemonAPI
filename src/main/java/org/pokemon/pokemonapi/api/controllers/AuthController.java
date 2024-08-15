package org.pokemon.pokemonapi.api.controllers;

import org.pokemon.pokemonapi.api.dto.AuthResponse;
import org.pokemon.pokemonapi.api.dto.LoginDTO;
import org.pokemon.pokemonapi.api.dto.RegisterDTO;
import org.pokemon.pokemonapi.api.models.Account;
import org.pokemon.pokemonapi.api.models.Role;
import org.pokemon.pokemonapi.api.repositories.AccountRepository;
import org.pokemon.pokemonapi.api.repositories.RoleRepository;
import org.pokemon.pokemonapi.api.security.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private JwtService jwtService;

    public AuthController(AuthenticationManager authenticationManager, AccountRepository accountRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.authenticationManager = authenticationManager;
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(
            @RequestBody LoginDTO loginDTO
    ) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginDTO.username()
                        , loginDTO.password()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtService.generateToken(authentication);
        AuthResponse authResponse = new AuthResponse(HttpStatus.OK.value(), token);
        return new ResponseEntity<AuthResponse>(authResponse, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @RequestBody RegisterDTO registerDTO
    ) {
        if (accountRepository.existsByUsername(registerDTO.username())) {
            return new ResponseEntity<String>("Username is taken!", HttpStatus.BAD_REQUEST);
        }
        Account account = new Account();
        account.setUsername(registerDTO.username());
        account.setPassword(passwordEncoder.encode(registerDTO.password()));

        Role role = roleRepository.findByName("USER").get();
        account.setRoles(Collections.singleton(role));

        accountRepository.save(account);
        return new ResponseEntity<String>("User registered success!", HttpStatus.CREATED);
    }
}
