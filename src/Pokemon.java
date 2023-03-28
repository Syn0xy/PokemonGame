package src;

import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Pokemon {
    private Image[] overworld;
    private String name;

    public Pokemon(String pathImage, String name){
        setImage(pathImage);
        this.name = name;
    }

    public Image[] getImage(){ return this.overworld; }
    public String getName(){ return this.name; }
    public void setImage(String path) {
        Image img = new ImageIcon(path + "/" + Main.PATH_POKEMON_ANIMATION).getImage();        
        int w = img.getWidth(null);
        int h = img.getHeight(null);
        overworld = new Image[w/h];
        for(int i = 0; i < overworld.length; i++)
            overworld[i] = toBufferedImage(img).getSubimage(i*h, 0, h, h);
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) { return (BufferedImage) img; }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
    }
}