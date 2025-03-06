package com.alea.pokedex.infrastructure.controller;

import com.alea.pokedex.application.PokemonCriteria;
import com.alea.pokedex.application.PokemonFilter;
import com.alea.pokedex.application.PokemonService;
import com.alea.pokedex.domain.Pokemon;
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

    @GetMapping
    public List<Pokemon> getHeaviestPokemons(@RequestParam(required = false) PokemonRequestCriteria criteria) {
        return pokemonService.getPokemonsBy(PokemonCriteria.fromRequestCriteria(criteria));
    }
}
