package com.alea.pokedex.infrastructure.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
class RestTemplateConfig {
    @Bean
    public RestTemplate restTemplate(PokemonApiProperties pokemonApiProperties) {
        RestTemplateBuilder restTemplateBuilder = new RestTemplateBuilder();
        restTemplateBuilder.rootUri(pokemonApiProperties.url());
        return restTemplateBuilder.build();
    }
}
