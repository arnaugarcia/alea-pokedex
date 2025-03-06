package com.alea.pokedex.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.StringUtils;

@ConfigurationProperties(prefix = "pokemon.api")
record PokemonApiProperties(String url) {

    PokemonApiProperties {
        if (!StringUtils.hasText(url)) {
            throw new IllegalArgumentException("url must not be empty");
        }
    }
}
