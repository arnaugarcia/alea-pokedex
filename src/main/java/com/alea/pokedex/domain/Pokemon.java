package com.alea.pokedex.domain;

import java.util.Objects;

public record Pokemon(String name, Integer weight, Integer height, Integer baseExperience) {

    public Pokemon {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(weight, "Weight cannot be null");
        Objects.requireNonNull(height, "Height cannot be null");
        Objects.requireNonNull(baseExperience, "Base experience cannot be null");
    }
}
