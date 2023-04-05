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
        int wOne = (w/h)/3;
        overworld = new Image[wOne * 4];
        for(int i = 0; i < wOne * 3; i++)
            overworld[i] = toBufferedImage(img).getSubimage(i*h, 0, h, h);
        for(int i = wOne * 3; i < overworld.length; i++)
            overworld[i] = flipImageHorizontally(overworld[i - wOne * 3]);
    }

    public static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) { return (BufferedImage) img; }
        BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        Graphics2D bGr = bimage.createGraphics();
        bGr.drawImage(img, 0, 0, null);
        bGr.dispose();
        return bimage;
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