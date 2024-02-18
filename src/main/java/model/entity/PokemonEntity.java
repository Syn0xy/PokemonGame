package model.entity;

import java.awt.Image;

import javax.swing.ImageIcon;

import model.terrain.Biome;
import model.terrain.EndlessTerrain;

public class PokemonEntity extends Entity {
    
    private Pokemon pokemon;
    
    private Direction direction;
    
    private boolean shiny;

    public PokemonEntity(Pokemon pokemon, int x, int y){
        super(new ImageIcon().getImage(), pokemon.name, x, y);
        this.pokemon = pokemon;
        this.direction = Direction.RIGHT;
        this.shiny = false;
    }

    public boolean isShiny() {
        return shiny;
    }

    @Override
    public Image getImage(){
        Image img = pokemon.overworld[0];
        if (direction == Direction.RIGHT) img = pokemon.overworld[6];
        else if (direction == Direction.UP) img = pokemon.overworld[2];
        else if (direction == Direction.DOWN) img = pokemon.overworld[4];
        return img;
    }

    @Override
    public void update() {
        if (Math.random() > 0.06) return;
        direction = Direction.values()[(int)(Math.random()*(Biome.values().length+1))];
        nextMove();
    }

    public void nextMove(){
        if (direction == Direction.RIGHT) if (nextMove(position.x + 1, position.y)) position.x++;
        if (direction == Direction.LEFT) if (nextMove(position.x - 1, position.y)) position.x--;
        if (direction == Direction.UP) if (nextMove(position.x, position.y - 1)) position.y--;
        if (direction == Direction.DOWN) if (nextMove(position.x, position.y + 1)) position.y++;
    }

    public boolean nextMove(int x, int y){
        return EndlessTerrain.getBlock(x, y).height != 2;
    }
}