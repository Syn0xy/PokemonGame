package model.entity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PokemonGenerator {

    private static final String POKEMON_PATH = "./res/pokemon/";
    
    private static PokemonGenerator singleton;

    private static String[] pokemonsName;

    private static Map<String, Pokemon> pokemons;

    public static final PokemonGenerator getInstance(){
        if(singleton == null) singleton = new PokemonGenerator();
        return singleton;
    }

    private PokemonGenerator(){
        init();
    }

    private void init(){
        File directory = new File(POKEMON_PATH);

        if(directory.exists()){
            pokemonsName = directory.list();
            pokemons = new HashMap<>();
            for (int i = 0; i < pokemonsName.length; i++) {
                String name = pokemonsName[i];
                File crntFile = new File("./res/pokemon/" + name + "/overworld.png");
                if(crntFile.exists()){
                    pokemons.put(name, new Pokemon(POKEMON_PATH + name, name));
                }else{
                    System.err.println(name + " n'existe pas !");
                }
            }
        }
    }

    public Pokemon getPokemon(){
        Pokemon p = null;
        do{
            p = getPokemon(pokemonsName[(int)(Math.random() * pokemonsName.length)]);
        }while(p == null);
        return p;
    }

    public Pokemon getPokemon(String pokemonName){
        return pokemons.get(pokemonName);
    }
}
