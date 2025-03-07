package com.alea.pokedex.infrastructure.adapter;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "pokemon.api")
record PokemonApiProperties(String url, Integer limit) {

    PokemonApiProperties {
        if (limit != null && limit <= 0) {
            throw new IllegalArgumentException("limit must be greater than 0");
        }
        if (url == null || url.isEmpty()) {
            throw new IllegalArgumentException("url must not be empty");
        }
    }
}
