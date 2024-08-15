package org.pokemon.pokemonapi.api.dto;

import java.util.Date;

public record AuthResponse(Date timestamp, int status, String token) {
    public AuthResponse(int status, String token) {
        this(new Date(), status, token);
    }
}
