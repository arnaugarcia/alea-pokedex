package com.alea.pokedex.application;

import com.alea.pokedex.infrastructure.controller.PokemonRequestCriteria;

public record PokemonCriteria(Integer limit, PokemonFilter filter) {

    public PokemonCriteria {
        if (limit != null && limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than 0");
        }
        if (filter != null && filter.equals(PokemonFilter.EXPERIENCE)) {
            throw new IllegalArgumentException("filter must not be experience");
        }
    }

    public boolean hasLimit() {
        return limit != null;
    }

    public boolean hasFilter() {
        return filter != null;
    }

    public static PokemonCriteria fromRequestCriteria(PokemonRequestCriteria requestCriteria) {
        return new PokemonCriteria(requestCriteria.limit(), PokemonFilter.valueOf(requestCriteria.filter()));
    }
}
