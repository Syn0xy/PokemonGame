package model.entity;

import java.awt.Image;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import view.ImageManager;

public class PokemonGenerator {

    //#region
    
    private static PokemonGenerator singleton;

    private static PokemonGenerator getInstance() {
        if (PokemonGenerator.singleton == null) {
            PokemonGenerator.singleton = new PokemonGenerator();
        }
        return PokemonGenerator.singleton;
    }

    //#endregion
    
    private final Map<String, Pokemon> indexedPokemons;

    private final Collection<Pokemon> pokemons;
    
    private PokemonGenerator() {
        this.indexedPokemons = new HashMap<>();
        this.pokemons = this.indexedPokemons.values();
        this.generate();
    }

    private Pokemon getPokemon(final String pokemonName) {
        return this.indexedPokemons.getOrDefault(pokemonName, null);
    }
    
    private Pokemon getRandomPokemon() {
        final int random = (int) (this.pokemons.size() * Math.random());
        return this.pokemons
            .stream()
            .skip(random)
            .findFirst()
            .orElse(null);
    }

    private void generate() {
        for (final Entry<String, Image> entry : ImageManager.getPokemonImages().entrySet()) {
            this.generatePokemon(
                entry.getKey(),
                entry.getValue()
            );
        }
    }

    private void generatePokemon(final String pokemonName, final Image pokemonImage) {
        this.indexedPokemons.putIfAbsent(pokemonName, new Pokemon(pokemonImage, pokemonName));
    }

    public static Pokemon get(final String pokemonName) {
        return PokemonGenerator.getInstance().getPokemon(pokemonName);
    }

    public static Pokemon getRandom() {
        return PokemonGenerator.getInstance().getRandomPokemon();
    }

}
