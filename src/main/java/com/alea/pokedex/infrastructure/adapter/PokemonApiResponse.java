package com.alea.pokedex.infrastructure.adapter;

import java.util.List;
import java.util.Objects;

record PokemonApiResponse(int count, String next, String previous, List<PokemonResult> results) {

    PokemonApiResponse {
        Objects.requireNonNull(results);
    }
}

record PokemonResult(String name, String url) {}

record PokemonDetails(String name, int weight, int height, int base_experience) {}
