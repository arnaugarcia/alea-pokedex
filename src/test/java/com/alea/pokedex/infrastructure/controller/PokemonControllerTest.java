package com.alea.pokedex.infrastructure.controller;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.alea.pokedex.IntegrationTest;
import com.alea.pokedex.domain.Pokemon;
import com.alea.pokedex.domain.PokemonRepository;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

@DisplayName("PokemonController")
class PokemonControllerTest extends IntegrationTest {

    @MockitoBean
    private PokemonRepository pokemonRepository;


    @Nested
    @DisplayName("GET /api/pokemons")
    class GetPokemons {

        @Test
        @DisplayName("should return a list of pokemons")
        void shouldReturnListOfPokemons() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );

            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(get("/api/pokemons"))
                .andExpect(status().isOk())
                    .andExpectAll(
                        jsonPath("$[0].name").value("ivysaur"),
                        jsonPath("$[0].weight").value(130),
                        jsonPath("$[0].height").value(10),
                        jsonPath("$[0].baseExperience").value(142),
                        jsonPath("$[1].name").value("bulbasaur"),
                        jsonPath("$[1].weight").value(69),
                        jsonPath("$[1].height").value(7),
                        jsonPath("$[1].baseExperience").value(64)
                    );
        }

        @Test
        @DisplayName("should return a list of pokemons sorted by weight")
        void shouldReturnListOfPokemonsSortedByWeight() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );
            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?sortBy=weight"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ivysaur"))
                .andExpect(jsonPath("$[1].name").value("bulbasaur")
            );

        }

        @Test
        @DisplayName("should return a list of pokemons sorted by height")
        void shouldReturnListOfPokemonsSortedByHeight() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );
            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?sortBy=height"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ivysaur"))
                .andExpect(jsonPath("$[1].name").value("bulbasaur")
            );
        }

        @Test
        @DisplayName("should return a list of pokemons sorted by base experience")
        void shouldReturnListOfPokemonsSortedByBaseExperience() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );

            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?sortBy=experience"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ivysaur"))
                .andExpect(jsonPath("$[1].name").value("bulbasaur")
            );
        }

        @Test
        @DisplayName("should return a list of pokemons sorted by weight and limited to 1")
        void shouldReturnListOfPokemonsSortedByWeightAndLimitedTo1() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );

            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?sortBy=weight&limit=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("ivysaur"));
        }

        @Test
        @DisplayName("should return an error when sortBy is invalid")
        void shouldReturnErrorWhenSortByIsInvalid() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );

            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?sortBy=invalid"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("should return an error when limit is invalid")
        void shouldReturnErrorWhenLimitIsInvalid() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );

            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?limit=invalid"))
                .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("should return an error when limit is less than 1")
        void shouldReturnErrorWhenSortByIsInvalidAndLimitIsInvalid() throws Exception {
            // Given
            var pokemons = List.of(
                new Pokemon("ivysaur", 130, 10, 142),
                new Pokemon("bulbasaur", 69, 7, 64)
            );

            when(pokemonRepository.getAllPokemons()).thenReturn(pokemons);

            // When
            mockMvc.perform(
                get("/api/pokemons?sortBy=invalid&limit=0"))
                .andExpect(status().isBadRequest());
        }

    }

}
