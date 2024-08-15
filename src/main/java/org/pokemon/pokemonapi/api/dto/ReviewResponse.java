package org.pokemon.pokemonapi.api.dto;

import java.util.List;

public record ReviewResponse(List<ReviewDTO> content, int pageNo, int pageSize, long totalElements, int totalPages,
                             boolean first, boolean last) {
}
