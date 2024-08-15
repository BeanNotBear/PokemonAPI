package org.pokemon.pokemonapi.api.services.impl;

import org.pokemon.pokemonapi.api.dto.PokemonDTO;
import org.pokemon.pokemonapi.api.dto.PokemonResponse;
import org.pokemon.pokemonapi.api.exceptions.PokemonNotFoundException;
import org.pokemon.pokemonapi.api.mappers.PokemonMapper;
import org.pokemon.pokemonapi.api.models.Pokemon;
import org.pokemon.pokemonapi.api.repositories.PokemonRepository;
import org.pokemon.pokemonapi.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private PokemonRepository pokemonRepository;
    private final String POKEMON_NOT_FOUND_MSG = "Pokemon is not found by ID";

    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonResponse findAll(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Pokemon> pokemons = pokemonRepository.findAll(pageable);
        List<PokemonDTO> pokemonDTOS = pokemons.getContent().stream()
                .map(pokemon -> PokemonMapper.toPokemonDTO(pokemon)).collect(Collectors.toList());
        PokemonResponse pokemonResponse = new PokemonResponse(
                pokemonDTOS,
                page,
                pageSize,
                pokemons.getTotalElements(),
                pokemons.getTotalPages(),
                pokemons.isFirst(),
                pokemons.isLast()
        );
        return pokemonResponse;
    }

    @Override
    public PokemonDTO findById(Integer id) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(POKEMON_NOT_FOUND_MSG));
        PokemonDTO pokemonDTO = PokemonMapper.toPokemonDTO(pokemon);
        return pokemonDTO;
    }

    @Override
    public PokemonDTO create(PokemonDTO pokemonDTO) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDTO.name());
        pokemon.setType(pokemonDTO.type());
        Pokemon insertedPokemon = pokemonRepository.save(pokemon);
        return PokemonMapper.toPokemonDTO(insertedPokemon);
    }

    @Override
    public PokemonDTO update(Integer id, PokemonDTO pokemonDTO) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(POKEMON_NOT_FOUND_MSG));
        pokemon.setName(pokemonDTO.name());
        pokemon.setType(pokemonDTO.type());
        Pokemon updatedPokemon = pokemonRepository.save(pokemon);
        return PokemonMapper.toPokemonDTO(updatedPokemon);
    }

    @Override
    public void delete(Integer id) {
        Pokemon p = pokemonRepository.findById(id)
                .orElseThrow(() -> new PokemonNotFoundException(POKEMON_NOT_FOUND_MSG));
        pokemonRepository.delete(p);
    }
}
