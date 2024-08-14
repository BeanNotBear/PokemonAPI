package org.pokemon.pokemonapi.api.controllers;

import org.pokemon.pokemonapi.api.dto.PokemonDTO;
import org.pokemon.pokemonapi.api.dto.PokemonResponse;
import org.pokemon.pokemonapi.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemon")
    public ResponseEntity<PokemonResponse> getAll(
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int pageSize
    ) {
        PokemonResponse pokemonResponse = pokemonService.findAll(page, pageSize);
        return new ResponseEntity<PokemonResponse>(pokemonResponse, HttpStatus.OK);
    }

    @GetMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDTO> gePokemonById(
            @PathVariable Integer id
    ) {
        PokemonDTO pokemon = pokemonService.findById(id);
        return new ResponseEntity<PokemonDTO>(pokemon, HttpStatus.OK);
    }

    @PostMapping("/pokemon")
    public ResponseEntity<PokemonDTO> creatPokemon(
            @RequestBody PokemonDTO pokemon
    ) {
        PokemonDTO createdPokemon = pokemonService.create(pokemon);
        return new ResponseEntity<PokemonDTO>(createdPokemon, HttpStatus.CREATED);
    }

    @PutMapping("/pokemon/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(
            @PathVariable Integer id,
            @RequestBody PokemonDTO pokemon
    ) {
        PokemonDTO updatedPokemon = pokemonService.update(id, pokemon);
        return new ResponseEntity<PokemonDTO>(updatedPokemon, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{id}")
    public ResponseEntity<Void> deletePokemon(
            @PathVariable Integer id
    ) {
        pokemonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
