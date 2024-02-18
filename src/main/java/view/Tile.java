package view;

import java.awt.Image;

import model.terrain.Biome;
import model.terrain.Block;

public class Tile {
    
    private Block block;
    
    private Biome biome;

    private Image image;

    private int width;

    private int height;
    
    public Tile(Block block, Biome biome){
        this.block = block;
        this.biome = biome;
        reloadImage();
    }

    public Block getBlock() {
        return block;
    }

    public Biome getBiome() {
        return biome;
    }

    public Image getImage() {
        return image;
    }
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setBlock(Block block) {
        this.block = block;
        reloadImage();
    }

    private void reloadImage(){
        this.image = ImageManager.get(block.name().toLowerCase());
        this.width = image.getWidth(null);
        this.height = image.getHeight(null);
    }

}