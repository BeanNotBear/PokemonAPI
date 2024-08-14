package org.pokemon.pokemonapi.api.mappers;

import org.pokemon.pokemonapi.api.dto.ReviewDTO;
import org.pokemon.pokemonapi.api.models.Review;

public class ReviewMapper {
    public static ReviewDTO toReviewDTO(Review review) {
        ReviewDTO reviewDTO = new ReviewDTO(review.getId(), review.getTitle(), review.getContent(), review.getStars());
        return reviewDTO;
    }

    public static Review toReviewEntity(ReviewDTO reviewDTO) {
        Review review = new Review();
        review.setTitle(reviewDTO.title());
        review.setContent(reviewDTO.content());
        review.setStars(reviewDTO.stars());
        return review;
    }
}
