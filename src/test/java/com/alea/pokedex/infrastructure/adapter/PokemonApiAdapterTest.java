package com.alea.pokedex.infrastructure.adapter;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.alea.pokedex.domain.Pokemon;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

@DisplayName("PokemonApiAdapter")
@ExtendWith(MockitoExtension.class)
class PokemonApiAdapterTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private PokemonApiProperties pokemonApiProperties;

    @InjectMocks
    private PokemonApiAdapter pokemonApiAdapter;

    @Nested
    @DisplayName("getAllPokemons()")
    class GetAllPokemons {

        @Test
        @DisplayName("should fetch all pokemons from the API and map them correctly")
        void shouldFetchAndMapPokemonsCorrectly() {
            // Given
            var pokemonResult1 = new PokemonResult("ivysaur", "url1");
            var pokemonResult2 = new PokemonResult("bulbasaur", "url2");
            var pokemonApiResponse = new PokemonApiResponse(2, null ,null, List.of(pokemonResult1, pokemonResult2));

            var pokemonDetails1 = new PokemonDetails("ivysaur", 130, 10, 142);
            var pokemonDetails2 = new PokemonDetails("bulbasaur", 69, 7, 64);

            when(pokemonApiProperties.url()).thenReturn("https://pokeapi.co");
            when(pokemonApiProperties.limit()).thenReturn(150);
            when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=150", PokemonApiResponse.class))
                .thenReturn(pokemonApiResponse);
            when(restTemplate.getForObject("url1", PokemonDetails.class)).thenReturn(pokemonDetails1);
            when(restTemplate.getForObject("url2", PokemonDetails.class)).thenReturn(pokemonDetails2);

            // When
            List<Pokemon> pokemons = pokemonApiAdapter.getAllPokemons();

            // Then
            assertThat(pokemons).hasSize(2);
            assertThat(pokemons.get(0).name()).isEqualTo("ivysaur");
            assertThat(pokemons.get(1).name()).isEqualTo("bulbasaur");
        }

        @Test
        @DisplayName("should call the external API once")
        void shouldCallExternalApiOnce() {
            // Given
            var pokemonResult1 = new PokemonResult("ivysaur", "url1");
            var pokemonApiResponse = new PokemonApiResponse(1, null ,null, List.of(pokemonResult1));
            var pokemonDetails1 = new PokemonDetails("ivysaur", 130, 10, 142);

            when(pokemonApiProperties.url()).thenReturn("https://pokeapi.co");
            when(pokemonApiProperties.limit()).thenReturn(150);
            when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=150", PokemonApiResponse.class))
                .thenReturn(pokemonApiResponse);
            when(restTemplate.getForObject("url1", PokemonDetails.class)).thenReturn(pokemonDetails1);

            // When
            pokemonApiAdapter.getAllPokemons();

            // Then
            verify(restTemplate, times(1)).getForObject("https://pokeapi.co/api/v2/pokemon?limit=150",
                PokemonApiResponse.class);
            verify(restTemplate, times(1)).getForObject("url1", PokemonDetails.class);
        }

        @Test
        @DisplayName("should return an empty list when API response is empty")
        void shouldReturnEmptyListWhenApiResponseIsEmpty() {
            // Given
            var pokemonApiResponse = new PokemonApiResponse(0, null, null, List.of());

            when(pokemonApiProperties.url()).thenReturn("https://pokeapi.co");
            when(pokemonApiProperties.limit()).thenReturn(150);
            when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=150", PokemonApiResponse.class))
                .thenReturn(pokemonApiResponse);

            // When
            List<Pokemon> pokemons = pokemonApiAdapter.getAllPokemons();

            // Then
            assertThat(pokemons).isEmpty();
        }

        @Test
        @DisplayName("should throw an exception when the API response is null")
        void shouldThrowExceptionWhenApiResponseIsNull() {
            // Given
            when(pokemonApiProperties.url()).thenReturn("https://pokeapi.co");
            when(pokemonApiProperties.limit()).thenReturn(150);
            when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=150", PokemonApiResponse.class))
                .thenReturn(null);

            // When & Then
            assertThatThrownBy(() -> pokemonApiAdapter.getAllPokemons())
                .isInstanceOf(NullPointerException.class);
        }

        @Test
        @DisplayName("should map Pokemon details correctly")
        void shouldMapPokemonDetailsCorrectly() {
            // Given
            var pokemonResult = new PokemonResult("ivysaur", "url1");
            var pokemonApiResponse = new PokemonApiResponse(1, null, null, List.of(pokemonResult));
            var pokemonDetails = new PokemonDetails("ivysaur", 130, 10, 142);

            when(pokemonApiProperties.url()).thenReturn("https://pokeapi.co");
            when(pokemonApiProperties.limit()).thenReturn(150);
            when(restTemplate.getForObject("https://pokeapi.co/api/v2/pokemon?limit=150", PokemonApiResponse.class))
                .thenReturn(pokemonApiResponse);
            when(restTemplate.getForObject("url1", PokemonDetails.class)).thenReturn(pokemonDetails);

            // When
            List<Pokemon> pokemons = pokemonApiAdapter.getAllPokemons();

            // Then
            assertThat(pokemons.get(0).name()).isEqualTo("ivysaur");
            assertThat(pokemons.get(0).weight()).isEqualTo(130);
            assertThat(pokemons.get(0).height()).isEqualTo(10);
            assertThat(pokemons.get(0).baseExperience()).isEqualTo(142);
        }
    }

    @Nested
    @DisplayName("fetchPokemonDetails()")
    class FetchPokemonDetails {

        @Test
        @DisplayName("should fetch the correct pokemon details")
        void shouldFetchCorrectPokemonDetails() {
            // Given
            var result = new PokemonResult("ivysaur", "url1");
            var pokemonDetails = new PokemonDetails("ivysaur", 130, 10, 142);

            when(restTemplate.getForObject("url1", PokemonDetails.class)).thenReturn(pokemonDetails);

            // When
            Pokemon pokemon = pokemonApiAdapter.fetchPokemonDetails(result);

            // Then
            assertThat(pokemon.name()).isEqualTo("ivysaur");
            assertThat(pokemon.weight()).isEqualTo(130);
            assertThat(pokemon.height()).isEqualTo(10);
            assertThat(pokemon.baseExperience()).isEqualTo(142);
        }

        @Test
        @DisplayName("should throw exception when pokemon details are null")
        void shouldThrowExceptionWhenPokemonDetailsAreNull() {
            // Given
            var result = new PokemonResult("ivysaur", "url1");

            when(restTemplate.getForObject("url1", PokemonDetails.class)).thenReturn(null);

            // When & Then
            assertThatThrownBy(() -> pokemonApiAdapter.fetchPokemonDetails(result))
                .isInstanceOf(NullPointerException.class);
        }
    }
}
