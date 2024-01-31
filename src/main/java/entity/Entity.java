package entity;

import java.awt.*;
import javax.swing.ImageIcon;

import util.Vector2;

public abstract class Entity {
    public Vector2 position;
    public int rotation;
    private boolean shiny;

    private Image image;

    public Entity(int positionX, int positionY, boolean shiny){
        this.position = new Vector2(positionX, positionY);
        this.shiny = shiny;
    }

    public Entity(int positionX, int positionY){
        this(positionX, positionY, false);
    }
    
    public Image getImage(){ return this.image; }
    public boolean isShiny(){ return this.shiny; }
    public void setImage(Image image) { this.image = image; }
    public void setImage(String path) {
        image = new ImageIcon(path).getImage();
    }
}
