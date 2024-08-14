package org.pokemon.pokemonapi.api.repositories;

import org.pokemon.pokemonapi.api.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName();
}