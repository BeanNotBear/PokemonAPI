package org.pokemon.pokemonapi.api.repositories;

import org.pokemon.pokemonapi.api.dto.ReviewDTO;
import org.pokemon.pokemonapi.api.models.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {
    Page<Review> findReviewsByPokemonId(Integer pokemonId, Pageable pageable);
}
