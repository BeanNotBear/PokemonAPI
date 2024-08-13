package org.pokemon.pokemonapi.api.controllers;

import org.pokemon.pokemonapi.api.dto.PokemonDTO;
import org.pokemon.pokemonapi.api.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// pokemon controller
@RestController
@RequestMapping("/api")
public class PokemonController {

    private PokemonService pokemonService;

    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/pokemons")
    public ResponseEntity<List<PokemonDTO>> getAll() {
        List<PokemonDTO> pokemons = pokemonService.findAll();
        return new ResponseEntity<List<PokemonDTO>>(pokemons, HttpStatus.OK);
    }

    @GetMapping("/pokemons/{id}")
    public ResponseEntity<PokemonDTO> gePokemonById(
            @PathVariable Integer id
    ) {
        PokemonDTO pokemon = pokemonService.findById(id);
        return new ResponseEntity<PokemonDTO>(pokemon, HttpStatus.OK);
    }

    @PostMapping("/pokemons")
    public ResponseEntity<PokemonDTO> creatPokemon(
            @RequestBody PokemonDTO pokemon
    ) {
        PokemonDTO createdPokemon = pokemonService.create(pokemon);
        return new ResponseEntity<PokemonDTO>(createdPokemon, HttpStatus.CREATED);
    }

    @PutMapping("/pokemons/{id}")
    public ResponseEntity<PokemonDTO> updatePokemon(
            @PathVariable Integer id,
            @RequestBody PokemonDTO pokemon
    ) {
        PokemonDTO updatedPokemon = pokemonService.update(id, pokemon);
        return new ResponseEntity<PokemonDTO>(updatedPokemon, HttpStatus.OK);
    }

    @DeleteMapping("/pokemons/{id}")
    public ResponseEntity<Void> deletePokemon(
            @PathVariable Integer id
    ) {
        pokemonService.delete(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
}
