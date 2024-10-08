package org.pokemon.pokemonapi.api.dto;

import java.util.Date;

public record ErrorResponse(Date timestamp, int status, String error) {
    public ErrorResponse(int status, String error) {
        this(new Date(), status, error);
    }
}
