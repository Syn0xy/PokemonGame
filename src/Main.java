package src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.io.*;

public class Main extends JFrame{
    //Main
    public static int WIDTH = 1280;
    public static int HEIGHT = 720;
    //PaintAllGraphics
    public final static int BLOCK_SIZE = 16;
    public final static int PIXEL_SIZE = 1;
    public final static int entitiesVisibleViewDst = (int)(Main.maxViewDst/1.5);
    //EndlessTerrain
    public final static int maxViewDst = 96;
    public final static int chunkSize = 32;
    public final static double noiseSize = 0.001;
    public final static double biomeSize = 0.005;
    //Tile
    public final static String PATH_GRAPHISM = "diamond_pearl";
    public final static String PATH_GRAPHISM_STATIC = "static";
    //Pokemon animation
    public final static String PATH_POKEMON_ANIMATION = "overworld.png";
    public final static String PATH_POKEMON_ANIMATION_SHINY = "overworld-shiny.png";

    private Timer timer;

    public static ArrayList<String> staticImage;

    private EndlessTerrain endlessTerrain;

    public void getAllImage(String path){
        File folder = new File(path);
        staticImage = new ArrayList<String>();
        for(int i = 0; i < folder.list().length; i++){
            staticImage.add(folder.list()[i]);
        }
    }

    private void getAllPokemon(String path){
        Spawn.allPokemon = new ArrayList<Pokemon>();
        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        for(int i = 0; i < listOfFiles.length; i++){
            File currentFile = new File("ressources/pokemon/" + listOfFiles[i].getName() + "/overworld.png");
            if(currentFile.exists()){
                String pathImage = "ressources/pokemon/" + listOfFiles[i].getName();
                Spawn.allPokemon.add(new Pokemon(pathImage, listOfFiles[i].getName()));
            }
        }
    }

    public Main() {
        setTitle("Games RPG");
        setSize(WIDTH, HEIGHT);
        setResizable(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getAllImage("ressources/img/diamond_pearl/static/");
        getAllPokemon("ressources/pokemon/");

        /*
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setUndecorated(true);
        setVisible(true);
        */

        getScreenSize();

        Spawn.spawnPlayer("test", -32, -12);
        PaintAllGraphics.cameraPosition = Spawn.player.position;
        endlessTerrain = new EndlessTerrain(Spawn.player.position);

        initGame();
        
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                getScreenSize();
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if (key == KeyEvent.VK_LEFT) { Spawn.player.movePlayer(Direction.LEFT); }
                if (key == KeyEvent.VK_RIGHT) { Spawn.player.movePlayer(Direction.RIGHT); }
                if (key == KeyEvent.VK_UP) { Spawn.player.movePlayer(Direction.UP); }
                if (key == KeyEvent.VK_DOWN) { Spawn.player.movePlayer(Direction.DOWN);}

                if (key == KeyEvent.VK_P) { debugDiplayChunk(); }
                if (key == KeyEvent.VK_L) { debugEnemy(); }
                if (key == KeyEvent.VK_O) { debugOutlineChunk(); }
                repaint();
                endlessTerrain.updateChunk();
            }
        });
    }

    private void initGame() {
        timer = new Timer(800, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
                endlessTerrain.updateChunk();
                Spawn.updateEntities();
            }
        });
        //timer.start();
    }

    @Override
    public void paint(Graphics g) {
        PaintAllGraphics.paintGraphics(g);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Main();
                frame.setVisible(true);
            }
        });
    }

    public void getScreenSize(){
        WIDTH = getContentPane().getWidth();
        HEIGHT = getContentPane().getHeight();
        PaintAllGraphics.offsetPosition = new Position(WIDTH / 2, HEIGHT / 2);
    }

    public static boolean debugBoolDisplayChunk = false;
    public static boolean debugBoolEnemy = false;
    public static boolean debugBoolOutlineChunk = false;
    public static void debugDiplayChunk(){ debugBoolDisplayChunk = !debugBoolDisplayChunk; }
    public static void debugEnemy(){ debugBoolEnemy = !debugBoolEnemy; }
    public static void debugOutlineChunk(){ debugBoolOutlineChunk = !debugBoolOutlineChunk; }
}