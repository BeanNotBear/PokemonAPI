package org.pokemon.pokemonapi.api.repositories;

import org.pokemon.pokemonapi.api.models.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PokemonRepository extends JpaRepository<Pokemon, Integer> {
}
