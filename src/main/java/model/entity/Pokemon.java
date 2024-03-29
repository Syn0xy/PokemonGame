package model.entity;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class Pokemon {
    
    protected String name;

    protected Image image;
    
    protected Image[] overworld;
    
    public Pokemon(Image image, String name){
        this.name = name;
        this.image = image;
        decompose();
    }

    public Image[] getImage(){ return this.overworld; }
    public String getName(){ return this.name; }
    public void decompose() {    
        int w = image.getWidth(null);
        int h = image.getHeight(null);
        int wOne = (w/h)/3;
        overworld = new Image[wOne * 4];
        for(int i = 0; i < wOne * 3; i++)
            overworld[i] = toBufferedImage(image).getSubimage(i*h, 0, h, h);
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