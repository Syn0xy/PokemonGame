package model.entity;

import java.awt.*;
import javax.swing.ImageIcon;

import model.util.Vector2;

public abstract class Entity {
    
    private String name;
    
    public Vector2 position;

    public Direction direction;
    
    private Image image;
    
    protected Entity(Image image, String name, int x, int y){
        this.image = image;
        this.name = name;
        this.position = new Vector2(x, y);
    }
    
    protected Entity(String pathImage, String name, int x, int y){
        this(new ImageIcon(pathImage).getImage(), name, x, y);
    }

    public String getName(){
        return name;
    }
    
    public Image getImage(){ return this.image; }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public abstract void update();

    protected boolean nextMove(int x, int y){
        return true;
    }

}
