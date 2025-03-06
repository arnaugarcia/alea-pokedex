package com.alea.pokedex.infrastructure.controller;

public record PokemonRequestCriteria(Integer limit, String filter) {

    public PokemonRequestCriteria {
        if (limit != null && limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than 0");
        }
        if (filter != null && filter.isEmpty()) {
            throw new IllegalArgumentException("filter must not be empty");
        }
    }
}
