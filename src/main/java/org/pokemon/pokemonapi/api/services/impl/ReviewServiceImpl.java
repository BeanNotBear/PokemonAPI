package org.pokemon.pokemonapi.api.services.impl;

import org.pokemon.pokemonapi.api.dto.PokemonDTO;
import org.pokemon.pokemonapi.api.dto.ReviewDTO;
import org.pokemon.pokemonapi.api.dto.ReviewResponse;
import org.pokemon.pokemonapi.api.exceptions.PokemonNotFoundException;
import org.pokemon.pokemonapi.api.exceptions.ReviewNotFoundException;
import org.pokemon.pokemonapi.api.mappers.ReviewMapper;
import org.pokemon.pokemonapi.api.models.Pokemon;
import org.pokemon.pokemonapi.api.models.Review;
import org.pokemon.pokemonapi.api.repositories.PokemonRepository;
import org.pokemon.pokemonapi.api.repositories.ReviewRepository;
import org.pokemon.pokemonapi.api.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;
    private PokemonRepository pokemonRepository;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PokemonRepository pokemonRepository) {
        this.reviewRepository = reviewRepository;
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public ReviewResponse findReviewByPokemonId(Integer pokemonId, int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Review> reviews = reviewRepository.findReviewsByPokemonId(pokemonId, pageable);
        List<ReviewDTO> reviewDTOs = reviews.getContent()
                .stream().map(review -> ReviewMapper.toReviewDTO(review)).collect(Collectors.toList());
        ReviewResponse reviewResponse = new ReviewResponse(
                reviewDTOs,
                page,
                pageSize,
                reviews.getTotalElements(),
                reviews.getTotalPages(),
                reviews.isFirst(),
                reviews.isLast()
        );
        return reviewResponse;
    }

    @Override
    public ReviewDTO createReview(Integer pokemonId, ReviewDTO reviewDTO) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = new Review();
        review.setTitle(reviewDTO.title());
        review.setContent(reviewDTO.content());
        review.setStars(reviewDTO.stars());
        review.setPokemon(pokemon);

        return ReviewMapper.toReviewDTO(reviewRepository.save(review));
    }

    @Override
    public ReviewDTO findReviewDetails(Integer reviewId, Integer pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }
        return ReviewMapper.toReviewDTO(review);
    }

    @Override
    public ReviewDTO updateReview(Integer pokemonId, Integer reviewId, ReviewDTO reviewDTO) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        review.setTitle(reviewDTO.title());
        review.setContent(reviewDTO.content());
        review.setStars(reviewDTO.stars());
        review.setPokemon(pokemon);

        Review updatedReview = reviewRepository.save(review);

        return ReviewMapper.toReviewDTO(updatedReview);
    }

    @Override
    public void deleteReview(Integer pokemonId, Integer reviewId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId)
                .orElseThrow(() -> new PokemonNotFoundException("Pokemon with associated review not found"));
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewNotFoundException("Review with associated pokemon not found"));

        if(review.getPokemon().getId() != pokemon.getId()) {
            throw new ReviewNotFoundException("This review does not belong to a pokemon");
        }

        reviewRepository.delete(review);
    }
}
