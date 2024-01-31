package view;

import static view.GameCanvas.BLOCK_SIZE;
import static view.GameCanvas.PATH_GRAPHISM;
import static view.GameCanvas.PATH_GRAPHISM_STATIC;
import static view.GameCanvas.staticImage;

import java.awt.Image;

import javax.swing.ImageIcon;

import terrain.Biome;
import terrain.Block;

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
        this.tileWidth = image.getWidth(null) / BLOCK_SIZE;
        this.tileHeight = image.getHeight(null) / BLOCK_SIZE;
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
    private void setBlock(){
        if (staticImage.contains(block.toString().toLowerCase() + ".png"))
            setImage("./res/img/" + PATH_GRAPHISM + "/" + PATH_GRAPHISM_STATIC + "/" + block.toString().toLowerCase() + ".png");
        else setImage("./res/img/" + PATH_GRAPHISM + "/" + biomeType + "/" + block.toString().toLowerCase() + ".png");
    }
}