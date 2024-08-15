package org.pokemon.pokemonapi.api.dto;

import java.util.List;

public record PokemonResponse(List<PokemonDTO> content, int pageNo, int pageSize, long totalElements, int totalPages,
                              boolean first, boolean last) {
}
