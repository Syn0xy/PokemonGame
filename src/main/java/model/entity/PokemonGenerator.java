package model.entity;

import java.awt.Image;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import view.ImageManager;

public class PokemonGenerator {
    
    private static PokemonGenerator singleton;

    private Map<String, Pokemon> pokemons;

    public static final PokemonGenerator getInstance(){
        if(singleton == null) singleton = new PokemonGenerator();
        return singleton;
    }

    private PokemonGenerator(){
        this.pokemons = new HashMap<>();
        for(Entry<String, Image> entry : ImageManager.getPokemonImages().entrySet()){
            String name = entry.getKey();
            Pokemon pokemon = new Pokemon(entry.getValue(), name);
            pokemons.put(name, pokemon);
        }
    }

    public Pokemon getPokemon(){
        List<String> keySet = new ArrayList<>(pokemons.keySet());
        return pokemons.get(keySet.get((int)(Math.random() * keySet.size())));
    }

    public Pokemon getPokemon(String pokemonName){
        return pokemons.get(pokemonName);
    }
}
