package org.pokemon.pokemonapi.api.services;
import org.pokemon.pokemonapi.api.dto.PokemonDTO;

import java.util.List;

public interface PokemonService {
    List <PokemonDTO> findAll();

    PokemonDTO findById(Integer id);

    PokemonDTO create(PokemonDTO pokemon);

    PokemonDTO update(Integer id, PokemonDTO pokemon);

    void delete(Integer id);
}
