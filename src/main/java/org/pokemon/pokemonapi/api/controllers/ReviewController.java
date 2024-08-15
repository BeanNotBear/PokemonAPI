package org.pokemon.pokemonapi.api.controllers;

import org.pokemon.pokemonapi.api.dto.ReviewDTO;
import org.pokemon.pokemonapi.api.dto.ReviewResponse;
import org.pokemon.pokemonapi.api.services.ReviewService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ReviewController {

    private ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewResponse> getReviewByPokemonId(
            @PathVariable Integer pokemonId,
            @RequestParam(defaultValue = "0", required = false) int page,
            @RequestParam(defaultValue = "10", required = false) int pageSize
    ) {
        ReviewResponse reviewResponse = reviewService.findReviewByPokemonId(pokemonId, page, pageSize);
        return new ResponseEntity<ReviewResponse>(reviewResponse, HttpStatus.OK);
    }

    @PostMapping("/pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDTO> createReview(
            @PathVariable Integer pokemonId,
            @RequestBody ReviewDTO review
    ) {
        ReviewDTO reviewDTO = reviewService.createReview(pokemonId, review);
        return new ResponseEntity<ReviewDTO>(reviewDTO, HttpStatus.CREATED);
    }

    @GetMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> getReviewById(
            @PathVariable Integer pokemonId,
            @PathVariable Integer reviewId
    ) {
        ReviewDTO reviewDTO = reviewService.findReviewDetails(reviewId, pokemonId);
        return new ResponseEntity<ReviewDTO>(reviewDTO, HttpStatus.OK);
    }

    @PutMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<ReviewDTO> updateReview(
            @PathVariable Integer pokemonId,
            @PathVariable Integer reviewId,
            @RequestBody ReviewDTO review
    ) {
        ReviewDTO reviewDTO = reviewService.updateReview(pokemonId, reviewId, review);

        return new ResponseEntity<ReviewDTO>(reviewDTO, HttpStatus.OK);
    }

    @DeleteMapping("/pokemon/{pokemonId}/reviews/{reviewId}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Integer pokemonId,
            @PathVariable Integer reviewId
    ) {
        reviewService.deleteReview(pokemonId, reviewId);

        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }

}
