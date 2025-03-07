package com.alea.pokedex.infrastructure.controller;

import com.alea.pokedex.application.PokemonCriteria;
import com.alea.pokedex.application.PokemonService;
import com.alea.pokedex.domain.Pokemon;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/pokemons")
class PokemonController {
    private final PokemonService pokemonService;

    // Document the api with annotations
    @Operation(summary = "Get a list of pokemons by criteria (limit, sortBy)")
    @GetMapping
    public List<Pokemon> getHeaviestPokemons(@RequestParam(value = "limit", required = false) Integer limit,
                                             @RequestParam(value = "sortBy", required = false) String sortBy) {
        return pokemonService.getPokemonsBy(PokemonCriteria.of(limit, sortBy));
    }
}
