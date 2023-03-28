package src;

import java.awt.*;
import javax.swing.ImageIcon;

public abstract class Entity {
    public Position position;
    public int rotation;
    private boolean shiny;

    private Image image;

    public Entity(int positionX, int positionY, boolean shiny){
        this.position = new Position(positionX, positionY);
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
