package entity;

import terrain.EndlessTerrain;

public class Player extends Entity{
    private String username;

    public Player(String username, int positionX, int positionY){
        super(positionX, positionY);
        this.username = username;
        setImage("./res/img/red.png");
    }

    public String getUsername(){ return this.username; }

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