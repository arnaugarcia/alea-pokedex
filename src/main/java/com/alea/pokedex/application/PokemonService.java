package com.alea.pokedex.application;

import static java.util.Comparator.comparingInt;

import com.alea.pokedex.domain.Pokemon;
import com.alea.pokedex.domain.PokemonRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PokemonService {
    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getPokemonsBy(PokemonCriteria criteria) {
        var pokemons = new ArrayList<>(pokemonRepository.getAllPokemons());
        if (criteria.hasSortBy()) {
            switch (criteria.sortBy()) {
                case HEAVIEST:
                    pokemons.sort(comparingInt(Pokemon::weight).reversed());
                    break;
                case TALLEST:
                    pokemons.sort(comparingInt(Pokemon::height).reversed());
                    break;
                case EXPERIENCE:
                    pokemons.sort(comparingInt(Pokemon::baseExperience).reversed());
                    break;
            }
        }

        if (criteria.hasLimit()) {
            return pokemons.stream()
                .limit(criteria.limit())
                .toList();
        }

        return pokemons;
    }
}
