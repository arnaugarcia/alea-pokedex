package com.alea.pokedex.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PokemonFilter {
    WEIGHT("weight"),
    HEIGHT("height"),
    EXPERIENCE("experience");
    private final String value;
}
