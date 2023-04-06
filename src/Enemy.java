package src;

import java.awt.Image;

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
        if (direction == Direction.RIGHT) if (nextMove(position.getPositionX()+1, position.getPositionY())) position.addPositionX(1);
        if (direction == Direction.LEFT) if (nextMove(position.getPositionX()-1, position.getPositionY())) position.addPositionX(-1);
        if (direction == Direction.UP) if (nextMove(position.getPositionX(), position.getPositionY()-1)) position.addPositionY(-1);
        if (direction == Direction.DOWN) if (nextMove(position.getPositionX(), position.getPositionY()+1)) position.addPositionY(1);
    }

    public boolean nextMove(int x, int y){
        return EndlessTerrain.getBlock(x, y).height != 2;
    }
}