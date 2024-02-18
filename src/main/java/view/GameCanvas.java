package view;

import static model.terrain.EndlessTerrain.chunkSize;
import static model.terrain.EndlessTerrain.maxViewDst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import model.GameScene;
import model.entity.Entity;
import model.entity.Player;
import model.terrain.EndlessTerrain;
import model.terrain.TerrainChunk;
import model.util.Vector2;

public class GameCanvas extends JPanel {
    
    public final static int BLOCK_SIZE = 16;
    public final static int PIXEL_SIZE = 2;
    public final static int entitiesVisibleViewDst = (int)(maxViewDst / 1.5);
    public final static int TILE_SIZE = BLOCK_SIZE * PIXEL_SIZE;
    
    public final static String PATH_GRAPHISM = "diamond_pearl";
    public final static String PATH_GRAPHISM_STATIC = "static";

    public static boolean debugBoolDisplayChunk = false;
    public static boolean debugBoolEnemy = false;
    public static boolean debugBoolOutlineChunk = false;

    public static ArrayList<String> staticImage;

    private List<Entity> entities;

    private Map<Vector2, TerrainChunk> chunks;

    private List<TerrainChunk> visibleChunks;

    private Player player;

    private Vector2 position;

    protected GameCanvas(GameScene gameScene){
        this.entities = gameScene.getEntities();
        EndlessTerrain endlessTerrain = gameScene.getEndlessTerrain();
        this.chunks = endlessTerrain.getTerrainChunks();
        this.visibleChunks = endlessTerrain.getTerrainChunksVisible();
        this.player = gameScene.getPlayer();
        this.position = player.position;
    }
    
    public static void getAllImage(){
        File folder = new File("./res/img/diamond_pearl/static/");
        String[] filesName = folder.list();
        staticImage = new ArrayList<>();
        for(int i = 0; i < filesName.length; i++){
            System.out.println("Load: " + filesName[i]);
            staticImage.add(filesName[i]);
        }
    }

    @Override
    public void paint(Graphics g) {
        clearScreen(g);
        g.translate(getWidth() / 2, getHeight() / 2);
        paintTerrainChunk(g);
        paintEntity(g);
        paintForegroundChunk(g);
        paintUI(g);
        g.translate(- getWidth() / 2, - getHeight() / 2);
        debug(g);
    }

    private void clearScreen(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void debug(Graphics g){
        if(debugBoolOutlineChunk && visibleChunks != null){
            g.setColor(Color.CYAN);
            int size = 2;
            int cs = chunkSize;
            for (TerrainChunk chunk : visibleChunks) {
                g.fillRect((chunk.position.x - position.x - cs) * TILE_SIZE, 0, size, getHeight());
                g.fillRect((chunk.position.x - position.x + cs) * TILE_SIZE, 0, size, getHeight());
                g.fillRect(0, (chunk.position.y - position.y - cs) * TILE_SIZE, getWidth(), size);
                g.fillRect(0, (chunk.position.y - position.y + cs) * TILE_SIZE, getWidth(), size);
            }
        }

        g.setFont(new Font("Arial", Font.BOLD, 15));
        g.setColor(Color.BLACK);

        if (debugBoolDisplayChunk && visibleChunks != null && chunks != null) {
            g.drawString("Nombre de chunks total : " + chunks.size(), 10, 50);
            g.drawString("Nombre de chunks chargés : " + visibleChunks.size() + " : ", 10, 65);
            int i = 1;
            for (TerrainChunk chunk : visibleChunks) {
                g.drawString(i + ". terrainChunk " + chunk.position, 40, 65 + 20 * i);
                i++;
            }
        }
        if (debugBoolEnemy && entities != null) {
            g.drawString("Nombre de d'entités total : " + entities.size(), 300, 65);
            int i = 1;
            for (Entity entity : entities) {
                g.drawString(i + ". entité " + entity.position, 340, 70 + 15 * i);
                i++;
            }
        }
    }

    private void paintUI(Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, (int)(BLOCK_SIZE * 0.75)));
        
        for (Entity entity : entities) {
            if(entity instanceof Player){
                g.setColor(Color.BLUE);
                g.setFont(new Font("Arial", Font.BOLD, (int)(BLOCK_SIZE * 0.75)));
                placeStringCenter(g, entity.getName(), entity.position.x, entity.position.y, 0, TILE_SIZE/2);
                g.setFont(new Font("Arial", Font.PLAIN, (int)(BLOCK_SIZE * 0.75)));
                g.setColor(Color.RED);
            }else{
                placeStringCenter(g, entity.getName(), entity.position.x, entity.position.y, 0, TILE_SIZE/2);
            }
        }
    }

    private void placeString(Graphics g, String str, int x, int y, int offsetX, int offsetY){
        g.drawString(str,
        x * TILE_SIZE - position.x * TILE_SIZE + offsetX,
        y * TILE_SIZE - position.y * TILE_SIZE - offsetY);
    }
    
    private void placeStringCenter(Graphics g, String str, int x, int y, int offsetX, int offsetY){
        int width = g.getFontMetrics().stringWidth(str);
        placeString(g, str, x, y, offsetX + TILE_SIZE/2 - width/2, offsetY);
    }

    private void placeImage(Graphics g, Image img, int x, int y){
        g.drawImage(img,
        (x * TILE_SIZE) - position.x * TILE_SIZE - (img.getWidth(null) * PIXEL_SIZE - TILE_SIZE),
        (y * TILE_SIZE) - position.y * TILE_SIZE - (img.getHeight(null) * PIXEL_SIZE - TILE_SIZE),
        img.getWidth(null) * PIXEL_SIZE, img.getHeight(null) * PIXEL_SIZE, null);
    }

    private void paintTerrainChunk(Graphics g){
        for (TerrainChunk chunk : visibleChunks) {
            paintChunkBackground(g, chunk);
        }
    }
    
    private void paintForegroundChunk(Graphics g){
        for (TerrainChunk chunk : visibleChunks) {
            paintChunkForeground(g, chunk);
        }
    }

    private void paintChunkBackground(Graphics g, TerrainChunk chunk){
        for(int y = 0; y < chunk.tilemaps.length; y++){
            for(int x = 0; x < chunk.tilemaps[y].length; x++){
                placeImage(g, chunk.tilemaps[y][x].getImage(),
                chunk.position.x + (x - chunk.tilemaps[y].length / 2),
                chunk.position.y + (y - chunk.tilemaps.length / 2));
            }
        }
        for (Map.Entry<Vector2, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 0)
                placeImage(g, f.getValue().getImage(), f.getKey().x, f.getKey().y);
        }
    }

    private void paintChunkForeground(Graphics g, TerrainChunk chunk){
        for (Map.Entry<Vector2, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 1)
                placeImage(g, f.getValue().getImage(), f.getKey().x, f.getKey().y);
        }
    }

    private void paintEntity(Graphics g){
        for (Entity entity : entities) {
            if (entity.position.distance(player.position) < entitiesVisibleViewDst){
                placeImage(g, entity.getImage(), entity.position.x, entity.position.y);
            }
        }
    }
}
