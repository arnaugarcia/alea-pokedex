package com.alea.pokedex.application;

import org.springframework.util.StringUtils;

public record PokemonCriteria(Integer limit, PokemonFilter sortBy) {

    public PokemonCriteria {
        if (limit != null && limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than 0");
        }
    }

    public boolean hasLimit() {
        return limit != null;
    }

    public boolean hasSortBy() {
        return sortBy != null;
    }

    public static PokemonCriteria of(Integer limit, String sortBy) {
        if (StringUtils.hasText(sortBy)) {
            var filter = PokemonFilter.valueOf(sortBy.toUpperCase());
            return new PokemonCriteria(limit, filter);
        }
        return new PokemonCriteria(limit, null);
    }
}
