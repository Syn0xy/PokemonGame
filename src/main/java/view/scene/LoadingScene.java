package view.scene;

import java.awt.Color;
import java.awt.Graphics;

import model.util.BufferedWriter;
import view.ImageManager;

public class LoadingScene extends Scene {

    private BufferedWriter bufferedWriter;
    
    @Override
    public void start(){
        this.bufferedWriter = new BufferedWriter(this);
        ImageManager.init(bufferedWriter);
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint ?");
        clearScreen(g);
        g.drawString(bufferedWriter.toString(), 0, 0);
    }

    public void clearScreen(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }
    
    public void update(String data) {
        repaint();
    }

}
