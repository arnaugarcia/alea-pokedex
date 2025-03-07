package com.alea.pokedex.infrastructure.controller;

import com.alea.pokedex.application.PokemonCriteria;
import com.alea.pokedex.application.PokemonService;
import com.alea.pokedex.domain.Pokemon;
import io.swagger.v3.oas.annotations.Operation;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/pokemons")
class PokemonController {

    private final PokemonService pokemonService;

    @GetMapping
    @Operation(summary = "Get a list of pokemons by criteria (limit, sortBy)")
    public List<Pokemon> getHeaviestPokemons(@RequestParam(value = "limit", required = false) Integer limit,
                                             @RequestParam(value = "sortBy", required = false) String sortBy) {
        log.debug("REST request to get a list of pokemons by criteria");
        return pokemonService.getPokemonsBy(PokemonCriteria.of(limit, sortBy));
    }
}
