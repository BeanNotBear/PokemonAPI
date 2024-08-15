package org.pokemon.pokemonapi.api.services;
import org.pokemon.pokemonapi.api.dto.PokemonDTO;
import org.pokemon.pokemonapi.api.dto.PokemonResponse;

public interface PokemonService {
    PokemonResponse findAll(int page, int pageSize);

    PokemonDTO findById(Integer id);

    PokemonDTO create(PokemonDTO pokemon);

    PokemonDTO update(Integer id, PokemonDTO pokemon);

    void delete(Integer id);
}
