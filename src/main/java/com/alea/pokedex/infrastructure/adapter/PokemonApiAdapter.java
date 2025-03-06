package com.alea.pokedex.infrastructure.adapter;

import com.alea.pokedex.domain.Pokemon;
import com.alea.pokedex.domain.PokemonRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
@RequiredArgsConstructor
public class PokemonApiAdapter implements PokemonRepository {

    private final RestTemplate restTemplate;

    @Override
    @Cacheable("pokemons")
    public List<Pokemon> getAllPokemons() {
        // The API doesn't support sorting so we need to fetch all pokemons and sort them in memory.
        // Keeping the limit to 150 for now.
        var response = restTemplate.getForObject("/api/v2/pokemon?limit=150", PokemonApiResponse.class);
        Objects.requireNonNull(response);
        return response.results().stream()
            .map(this::fetchPokemonDetails)
            .toList();
    }

    private Pokemon fetchPokemonDetails(PokemonResult result) {
        var details = restTemplate.getForObject(result.url(), PokemonDetails.class);
        Objects.requireNonNull(details);
        return new Pokemon(details.name(), details.weight(), details.height(), details.base_experience());
    }
}
