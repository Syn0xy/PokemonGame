package src;

import java.util.ArrayList;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Spawn {
    public static Map<String, Pokemon> allPokemon = new HashMap<String, Pokemon>();
    public static ArrayList<Enemy> entities = new ArrayList<Enemy>();
    public static String[] allPokemonName;
    public static Player player;

    public static void setPokemonBase(String path){
        allPokemonName = new File(path).list();
    }

    public static Pokemon getPokemon(String pokemonName){
        if(!allPokemon.containsKey(pokemonName)){
            System.out.println("New pokemon : " + allPokemon.size() + " : " + pokemonName);
            allPokemon.put(pokemonName, new Pokemon("ressources/pokemon/" + pokemonName, pokemonName));
        }
        return allPokemon.get(pokemonName);
    }

    public static Pokemon getPokemon(){
        String pName;
        do{
            pName = allPokemonName[(int)(Math.random() * allPokemonName.length)];
        }while(!pokemonNotExists(pName));
        return getPokemon(pName);
    }

    public static boolean pokemonNotExists(String pokemonName){
        if(new File("ressources/pokemon/" + pokemonName + "/overworld.png").exists())
            return true;
        return false;
    }

    public static void spawnPlayer(String str, int x, int y){
        player = new Player(str, x, y);
    }

    public static void spawnEntity(Pokemon pokemon, int x, int y){
        entities.add(new Enemy(pokemon, x, y));
    }
    public static void spawnEntity(int x, int y){
        spawnEntity(getPokemon(), x, y);
    }

    public static void updateEntities(){
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).nextMove();
        }
    }
}