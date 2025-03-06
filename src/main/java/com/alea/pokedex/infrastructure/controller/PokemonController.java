package com.alea.pokedex.infrastructure.controller;

import com.alea.pokedex.application.PokemonService;
import com.alea.pokedex.domain.Pokemon;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pokemons")
class PokemonController {
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/heaviest")
    public List<Pokemon> getHeaviestPokemons() {
        return pokemonService.getHeaviestPokemons();
    }

    @GetMapping("/tallest")
    public List<Pokemon> getTallestPokemons() {
        return pokemonService.getTallestPokemons();
    }

    @GetMapping("/experienced")
    public List<Pokemon> getMostExperiencedPokemons() {
        return pokemonService.getMostExperiencedPokemons();
    }
}
