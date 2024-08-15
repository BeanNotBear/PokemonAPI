package org.pokemon.pokemonapi.api.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
public class SecurityConstants {

    public static long JWT_EXPIRATION = 86400000;

}
