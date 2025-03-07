package com.alea.pokedex.domain;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Pokemon domain test")
class PokemonDomainTest {

    @Test
    void name() {
        // Given
        String name = "Ivysaur";
        Integer weight = 130;
        Integer height = 10;
        Integer baseExperience = 142;

        // When
        Pokemon pokemon = new Pokemon(name, weight, height, baseExperience);

        // Then
        assertThat(pokemon.name()).isEqualTo(name);
    }

    @Test
    void weight() {
        // Given
        String name = "Ivysaur";
        Integer weight = 130;
        Integer height = 10;
        Integer baseExperience = 142;

        // When
        Pokemon pokemon = new Pokemon(name, weight, height, baseExperience);

        // Then
        assertThat(pokemon.weight()).isEqualTo(weight);
    }

    @Test
    void height() {
        // Given
        String name = "Ivysaur";
        Integer weight = 130;
        Integer height = 10;
        Integer baseExperience = 142;

        // When
        Pokemon pokemon = new Pokemon(name, weight, height, baseExperience);

        // Then
        assertThat(pokemon.height()).isEqualTo(height);
    }

    @Test
    void baseExperience() {
        // Given
        String name = "Ivysaur";
        Integer weight = 130;
        Integer height = 10;
        Integer baseExperience = 142;

        // When
        Pokemon pokemon = new Pokemon(name, weight, height, baseExperience);

        // Then
        assertThat(pokemon.baseExperience()).isEqualTo(baseExperience);
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        // Given
        Integer weight = 130;
        Integer height = 10;
        Integer baseExperience = 142;

        // When & Then
        assertThatThrownBy(() -> new Pokemon(null, weight, height, baseExperience))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Name");
    }

    @Test
    void shouldThrowExceptionWhenWeightIsNull() {
        // Given
        String name = "Ivysaur";
        Integer height = 10;
        Integer baseExperience = 142;

        // When & Then
        assertThatThrownBy(() -> new Pokemon(name, null, height, baseExperience))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Weight");
    }

    @Test
    void shouldThrowExceptionWhenHeightIsNull() {
        // Given
        String name = "Ivysaur";
        Integer weight = 130;
        Integer baseExperience = 142;

        // When & Then
        assertThatThrownBy(() -> new Pokemon(name, weight, null, baseExperience))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Height");
    }

    @Test
    void shouldThrowExceptionWhenBaseExperienceIsNull() {
        // Given
        String name = "Ivysaur";
        Integer weight = 130;
        Integer height = 10;

        // When & Then
        assertThatThrownBy(() -> new Pokemon(name, weight, height, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("Base experience");
    }
}
