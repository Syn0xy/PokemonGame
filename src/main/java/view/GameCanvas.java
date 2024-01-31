package view;

import static terrain.EndlessTerrain.chunkSize;
import static terrain.EndlessTerrain.maxViewDst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JPanel;

import entity.Enemy;
import entity.Player;
import entity.Spawn;
import terrain.EndlessTerrain;
import terrain.TerrainChunk;
import util.Vector2;

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
    
    public static Vector2 cameraPosition;
    public static Vector2 offsetPosition;
    
    public static void getAllImage(String path){
        File folder = new File(path);
        String[] filesName = folder.list();
        staticImage = new ArrayList<String>();
        for(int i = 0; i < filesName.length; i++){
            System.out.println("Load: " + filesName);
            staticImage.add(filesName[i]);
        }
    }

    @Override
    public void paint(Graphics g) {
        offsetPosition = new Vector2(getWidth() / 2, getHeight() / 2);
        clearScreen(g);
        paintTerrainChunk(g, EndlessTerrain.terrainChunksVisible);
        paintEntity(g, Spawn.entities);
        paintPlayer(g, Spawn.player);
        paintForegroundChunk(g, EndlessTerrain.terrainChunksVisible);
        paintUI(g, Spawn.player, Spawn.entities);
        debug(g, EndlessTerrain.terrainChunk, EndlessTerrain.terrainChunksVisible, Spawn.entities);
    }

    private void clearScreen(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    public void debug(Graphics g, Map<Vector2, TerrainChunk> terrainChunk, ArrayList<TerrainChunk> terrainChunksVisible, ArrayList<Enemy> allEntities){
        
        if (debugBoolDisplayChunk && terrainChunksVisible != null && terrainChunk != null) {
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.setColor(Color.ORANGE);
            g.drawString("Nombre de chunks total : " + terrainChunk.size(), 10, 50);
            g.drawString("Nombre de chunks chargés : " + terrainChunksVisible.size() + " : ", 10, 65);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            for(int i = 0; i < terrainChunksVisible.size(); i++){
                g.drawString((i+1) + ". terrainChunk " + terrainChunksVisible.get(i).position, 40, 65 + 20 * (i+1));
            }
        }
        if(debugBoolOutlineChunk && terrainChunksVisible != null){
            g.setColor(Color.CYAN);
            int size = 2;
            int CS = chunkSize;
            for(int i = 0; i < terrainChunksVisible.size(); i++){
                g.fillRect(
                (terrainChunksVisible.get(i).position.x - CS/2) * TILE_SIZE - cameraPosition.x * TILE_SIZE + offsetPosition.x,
                0, size, getHeight());
                g.fillRect(
                (terrainChunksVisible.get(i).position.x + CS/2) * TILE_SIZE - cameraPosition.x * TILE_SIZE + offsetPosition.x,
                0, size, getHeight());
            }
            for(int i = 0; i < terrainChunksVisible.size(); i++){
                g.fillRect(0,
                (terrainChunksVisible.get(i).position.y + CS/2) * TILE_SIZE - cameraPosition.y * TILE_SIZE + offsetPosition.y,
                getWidth(), size);
                g.fillRect(0,
                (terrainChunksVisible.get(i).position.y - CS/2) * TILE_SIZE - cameraPosition.y * TILE_SIZE + offsetPosition.y,
                getWidth(), size);
            }
        }
        if (debugBoolEnemy && allEntities != null) {
            g.setColor(Color.ORANGE);
            g.drawString("Nombre de d'entités total : " + allEntities.size(), 250, 65);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            for(int i = 0; i < allEntities.size(); i++){
                g.drawString((i+1) + ". entité " + allEntities.get(i).position, 290, 70 + 15 * (i+1));
            }
        }
    }

    public static void paintUI(Graphics g, Player player, ArrayList<Enemy> allEntities){
        g.setColor(Color.BLUE);
        g.setFont(new Font("Arial", Font.BOLD, (int)(BLOCK_SIZE * 0.75)));
        placeStringCenter(g, player.getUsername() + " " + player.position, player.position.x, player.position.y, 0, TILE_SIZE/2);

        g.setFont(new Font("Arial", Font.PLAIN, (int)(BLOCK_SIZE * 0.75)));
        g.setColor(Color.RED);
        for(int i = 0; i < allEntities.size();i++){
            Enemy entity = allEntities.get(i);
            placeStringCenter(g, entity.getName(), entity.position.x, entity.position.y, 0, TILE_SIZE/2);
        }
    }

    public static void placePixel(Graphics g, int x, int y){
        g.fillRect(
        (x * TILE_SIZE) - cameraPosition.x * TILE_SIZE + offsetPosition.x,
        (y * TILE_SIZE) - cameraPosition.y * TILE_SIZE + offsetPosition.y,
        TILE_SIZE,
        TILE_SIZE);
    }

    public static void placeString(Graphics g, String str, int x, int y, int offsetX, int offsetY){
        g.drawString(str,
        x * TILE_SIZE - cameraPosition.x * TILE_SIZE + offsetPosition.x + offsetX,
        y * TILE_SIZE - cameraPosition.y * TILE_SIZE + offsetPosition.y - offsetY);
    }
    
    public static void placeStringCenter(Graphics g, String str, int x, int y, int offsetX, int offsetY){
        int width = g.getFontMetrics().stringWidth(str);
        placeString(g, str, x, y, offsetX + TILE_SIZE/2 - width/2, offsetY);
    }

    public static void placeImage(Graphics g, Image img, int x, int y){
        g.drawImage(img,
        (x * TILE_SIZE) - cameraPosition.x * TILE_SIZE + offsetPosition.x - (img.getWidth(null) * PIXEL_SIZE - TILE_SIZE),
        (y * TILE_SIZE) - cameraPosition.y * TILE_SIZE + offsetPosition.y - (img.getHeight(null) * PIXEL_SIZE - TILE_SIZE),
        img.getWidth(null) * PIXEL_SIZE, img.getHeight(null) * PIXEL_SIZE, null);
    }

    public static void paintTerrainChunk(Graphics g, ArrayList<TerrainChunk> terrainChunksVisible){
        for(int i = 0; i < terrainChunksVisible.size(); i++)
            paintChunkBackground(g, terrainChunksVisible.get(i));
    }
    
    public static void paintForegroundChunk(Graphics g, ArrayList<TerrainChunk> terrainChunksVisible){
        for(int i = 0; i < terrainChunksVisible.size(); i++)
            paintChunkForeground(g, terrainChunksVisible.get(i));
    }

    public static void paintChunkBackground(Graphics g, TerrainChunk chunk){
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

    public static void paintChunkForeground(Graphics g, TerrainChunk chunk){
        for (Map.Entry<Vector2, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 1)
                placeImage(g, f.getValue().getImage(), f.getKey().x, f.getKey().y);
        }
    }
    
    public static void paintPlayer(Graphics g, Player player){
        placeImage(g, player.getImage(), player.position.x, player.position.y);
    }

    public static void paintEntity(Graphics g, ArrayList<Enemy> allEntities){
        if (allEntities == null) return;
        for(int i = 0; i < allEntities.size();i++){
            Enemy entity = allEntities.get(i);
            if (entity.position.distance(Spawn.player.position) < entitiesVisibleViewDst){
                placeImage(g, entity.getImage(), entity.position.x, entity.position.y);
            }
        }
    }
}
