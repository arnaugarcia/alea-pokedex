package com.alea.pokedex.application;

import com.alea.pokedex.domain.Pokemon;
import com.alea.pokedex.domain.PokemonRepository;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getHeaviestPokemons() {
        return pokemonRepository.getAllPokemons()
            .stream()
            .sorted(Comparator.comparingInt(Pokemon::weight).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }

    public List<Pokemon> getTallestPokemons() {
        return pokemonRepository.getAllPokemons()
            .stream()
            .sorted(Comparator.comparingInt(Pokemon::height).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }

    public List<Pokemon> getMostExperiencedPokemons() {
        return pokemonRepository.getAllPokemons()
            .stream()
            .sorted(Comparator.comparingInt(Pokemon::baseExperience).reversed())
            .limit(5)
            .collect(Collectors.toList());
    }
}
