package model.entity;

import model.terrain.EndlessTerrain;

public class Player extends Entity {

    private static final String IMAGE_PATH = "./res/img/red.png";

    public Player(String name, int positionX, int positionY){
        super(IMAGE_PATH, name, positionX, positionY);
    }

    @Override
    public String getName() {
        return super.getName() + " - " + position;
    }

    @Override
    public void update() {}

    public void movePlayer(Direction direction){
        if (direction == Direction.RIGHT) { // DROITE
            if (nextMove(position.x + 1, position.y)) position.x++;
        }
        if (direction == Direction.LEFT) { // GAUCHE
            if (nextMove(position.x - 1, position.y)) position.x--;
        }
        if (direction == Direction.UP) { // HAUT
            if (nextMove(position.x, position.y - 1)) position.y--;
        }
        if (direction == Direction.DOWN) { // BAS
            if (nextMove(position.x, position.y + 1)) position.y++;
        }
    }

    public boolean nextMove(int x, int y){
        return EndlessTerrain.getBlock(x, y).height != 2;
    }
}