package org.pokemon.pokemonapi.api.services;

import org.pokemon.pokemonapi.api.dto.ReviewDTO;
import org.pokemon.pokemonapi.api.dto.ReviewResponse;

import java.util.List;

public interface ReviewService {
    ReviewResponse findReviewByPokemonId(Integer id, int page, int pageSize);

    ReviewDTO createReview(Integer pokemonId, ReviewDTO reviewDTO);

    ReviewDTO findReviewDetails(Integer reviewId, Integer pokemonId);

    ReviewDTO updateReview(Integer pokemonId, Integer reviewId, ReviewDTO reviewDTO);

    void deleteReview(Integer pokemonId, Integer reviewId);
}
