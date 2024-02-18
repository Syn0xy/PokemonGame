package model.entity;

import java.util.ArrayList;
import java.util.List;

public class Spawn {
    public static List<Entity> entities = new ArrayList<Entity>();
    public static Player player;

    public static Player spawnPlayer(String name, int x, int y){
        player = new Player(name, x, y);
        entities.add(player);
        return player;
    }

    public static void spawnEntity(Pokemon pokemon, int x, int y){
        entities.add(new PokemonEntity(pokemon, x, y));
    }
    public static void spawnEntity(int x, int y){
        spawnEntity(PokemonGenerator.getInstance().getPokemon(), x, y);
    }

    public static void updateEntities(){
        for (Entity entity : entities) {
            entity.update();
        }
    }
}