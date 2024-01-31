package entity;

import java.awt.Image;

import terrain.Biome;
import terrain.EndlessTerrain;

public class Enemy extends Entity{
    private Pokemon pokemon;
    private Direction direction;

    public Enemy(Pokemon pokemon, int positionX, int positionY){
        super(positionX, positionY);
        this.pokemon = pokemon;
        this.direction = Direction.RIGHT;
    }

    public void setDirection(Direction direction){ this.direction = direction; }

    public Pokemon getPokemon(){ return this.pokemon; }
    public Direction getDirection(){ return this.direction; }
    public String getName(){ return this.pokemon.getName(); }
    public Image getImage(){
        Image img = pokemon.getImage()[0];
        if (direction == Direction.RIGHT) img = pokemon.getImage()[6];
        else if (direction == Direction.UP) img = pokemon.getImage()[2];
        else if (direction == Direction.DOWN) img = pokemon.getImage()[4];
        return img;
    }

    public void nextMove(){
        if (Math.random() > 0.6) return;
        direction = Direction.values()[(int)(Math.random()*(Biome.values().length+1))];
        if (direction == Direction.RIGHT) if (nextMove(position.x + 1, position.y)) position.x++;
        if (direction == Direction.LEFT) if (nextMove(position.x - 1, position.y)) position.x--;
        if (direction == Direction.UP) if (nextMove(position.x, position.y - 1)) position.y--;
        if (direction == Direction.DOWN) if (nextMove(position.x, position.y + 1)) position.y++;
    }

    public boolean nextMove(int x, int y){
        return EndlessTerrain.getBlock(x, y).height != 2;
    }
}