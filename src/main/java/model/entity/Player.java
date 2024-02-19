package model.entity;

import java.awt.event.KeyEvent;

import model.input.Input;
import model.input.KeyCode;

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
    public void update() {
        if (Input.getKey(KeyCode.D)) {
            if (nextMove(position.x + 1, position.y)) position.x++;
        }
        if (Input.getKey(KeyCode.Q)) {
            if (nextMove(position.x - 1, position.y)) position.x--;
        }
        if (Input.getKey(KeyCode.Z)) {
            if (nextMove(position.x, position.y - 1)) position.y--;
        }
        if (Input.getKey(KeyCode.S)) {
            if (nextMove(position.x, position.y + 1)) position.y++;
        }
    }
}