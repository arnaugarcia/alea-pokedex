package com.alea.pokedex.application;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PokemonFilter {
    HEAVIEST("heaviest"),
    TALLEST("tallest"),
    EXPERIENCE("experience");
    private final String value;
}
