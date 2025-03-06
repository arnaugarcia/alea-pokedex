package com.alea.pokedex.infrastructure.adapter;

import com.alea.pokedex.domain.Pokemon;
import com.alea.pokedex.domain.PokemonRepository;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
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
        String url = "https://pokeapi.co/api/v2/pokemon?limit=150";
        var response = restTemplate.getForObject(url, PokemonApiResponse.class);
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
