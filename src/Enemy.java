package src;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

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
        if(true) return img;
        if (direction == Direction.RIGHT){
            img = flipImageHorizontally(img);
        }
        if (direction == Direction.LEFT){
            img = pokemon.getImage()[0];
        }
        if (direction == Direction.UP){
            img = pokemon.getImage()[2];
        }
        if (direction == Direction.DOWN){
            img = pokemon.getImage()[4];
        }
        return img;
    }

    public void nextMove(){
        if (Math.random() > 0.4) return;
        direction = Direction.values()[(int)(Math.random()*(Biome.values().length+1))];
        if (direction == Direction.RIGHT) if (nextMove(position.getPositionX()+1, position.getPositionY())) position.addPositionX(1);
        if (direction == Direction.LEFT) if (nextMove(position.getPositionX()-1, position.getPositionY())) position.addPositionX(-1);
        if (direction == Direction.UP) if (nextMove(position.getPositionX(), position.getPositionY()-1)) position.addPositionY(-1);
        if (direction == Direction.DOWN) if (nextMove(position.getPositionX(), position.getPositionY()+1)) position.addPositionY(1);
    }

    public boolean nextMove(int x, int y){
        return EndlessTerrain.getBlock(x, y).height != 2;
    }

    public static Image flipImageVertically(Image img) {
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, 0, h, w, 0, null);
        g.dispose();
        return bimg;
    }
    public static Image flipImageHorizontally(Image img) {
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        BufferedImage bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = bimg.createGraphics();
        g.drawImage(img, 0, 0, w, h, w, 0, 0, h, null);
        g.dispose();
        return bimg;
    }
}