package src;

import java.util.ArrayList;

public class Spawn {
    public static ArrayList<Pokemon> allPokemon = new ArrayList<Pokemon>();
    public static ArrayList<Enemy> entities = new ArrayList<Enemy>();
    public static Player player;

    public static void spawnPlayer(String str, int x, int y){
        player = new Player(str, x, y);
    }

    public static void spawnEntity(Pokemon pokemon, int x, int y){
        entities.add(new Enemy(pokemon, x, y));
    }
    public static void spawnEntity(int x, int y){
        if (allPokemon.size() != 0){
            Pokemon pokemon = allPokemon.get((int)(Math.random() * allPokemon.size()));
            spawnEntity(pokemon, x, y);
        }
    }

    public static void updateEntities(){
        for(int i = 0; i < entities.size(); i++){
            entities.get(i).nextMove();
        }
    }
}