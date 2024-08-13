package org.pokemon.pokemonapi.api.mappers;

import org.pokemon.pokemonapi.api.dto.PokemonDTO;
import org.pokemon.pokemonapi.api.models.Pokemon;

public class PokemonMapper {
    public static PokemonDTO toPokemonDTO(Pokemon pokemon) {
        PokemonDTO pokemonDTO = new PokemonDTO(pokemon.getId(), pokemon.getName(), pokemon.getType());
        return pokemonDTO;
    }
}
