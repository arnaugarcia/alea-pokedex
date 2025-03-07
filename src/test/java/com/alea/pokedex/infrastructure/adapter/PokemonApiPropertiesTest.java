package com.alea.pokedex.infrastructure.adapter;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("PokemonApiProperties Test")
class PokemonApiPropertiesTest {

    @Test
    void shouldCreatePokemonApiPropertiesSuccessfully() {
        // Given
        String url = "https://pokeapi.co/api/v2/pokemon";
        Integer limit = 100;

        // When
        PokemonApiProperties properties = new PokemonApiProperties(url, limit);

        // Then
        assertThat(properties.url()).isEqualTo(url);
        assertThat(properties.limit()).isEqualTo(limit);
    }

    @Test
    void shouldThrowExceptionWhenLimitIsZero() {
        // Given
        String url = "https://pokeapi.co/api/v2/pokemon";
        Integer limit = 0;

        // Then
        assertThatThrownBy(() -> new PokemonApiProperties(url, limit))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("limit must be greater than 0");
    }

    @Test
    void shouldThrowExceptionWhenLimitIsNegative() {
        // Given
        String url = "https://pokeapi.co/api/v2/pokemon";

        // Test different negative values to cover all branches
        assertThatThrownBy(() -> new PokemonApiProperties(url, -1))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("limit must be greater than 0");

        assertThatThrownBy(() -> new PokemonApiProperties(url, -10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("limit must be greater than 0");
    }

    @Test
    void shouldThrowExceptionWhenUrlIsNull() {
        // Given
        Integer limit = 100;

        // Then
        assertThatThrownBy(() -> new PokemonApiProperties(null, limit))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("url must not be empty");
    }

    @Test
    void shouldThrowExceptionWhenUrlIsEmpty() {
        // Given
        Integer limit = 100;

        // Then
        assertThatThrownBy(() -> new PokemonApiProperties("", limit))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("url must not be empty");
    }
}
