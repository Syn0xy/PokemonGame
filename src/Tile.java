package src;

import java.awt.*;
import javax.swing.ImageIcon;

public class Tile {

    private Block block;
    private Biome biome;
    private Image image;

    private int tileWidth;
    private int tileHeight;

    private String biomeType = "./";

    public Tile(Block block, Biome biome, int tileWidth, int tileHeight){
        this.block = block;
        this.tileWidth = tileWidth;
        this.tileHeight = tileHeight;
        setBiome(biome);
    }
    public Tile(Block block, Biome biome){
        this(block, biome, 1, 1);
    }

    public Block getBlock(){ return this.block; }
    public Biome getBiome(){ return this.biome; }
    public Image getImage(){ return this.image; }
    public int getWidth(){ return this.tileWidth; }
    public int getHeight(){ return this.tileHeight; }
    public void setImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        image = icon.getImage();
        this.tileWidth = image.getWidth(null) / Main.BLOCK_SIZE;
        this.tileHeight = image.getHeight(null) / Main.BLOCK_SIZE;
    }
    public void setBlock(Block block){
        this.block = block;
        setBlock();
    }
    public void setBiome(Biome biome){
        this.biome = biome;
        this.biomeType = biome.toString().toLowerCase();
        setBlock();
    }
    public void setBlock(){
        if (Main.staticImage.contains(block.toString().toLowerCase() + ".png"))
            setImage("ressources/img/" + Main.PATH_GRAPHISM + "/" + Main.PATH_GRAPHISM_STATIC + "/" + block.toString().toLowerCase() + ".png");
        else setImage("ressources/img/" + Main.PATH_GRAPHISM + "/" + biomeType + "/" + block.toString().toLowerCase() + ".png");
    }
}