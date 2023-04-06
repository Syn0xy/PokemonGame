package src;

import java.util.ArrayList;
import java.util.Map;
import java.awt.*;

public class PaintAllGraphics {
    public final static int TILE_SIZE = Main.BLOCK_SIZE * Main.PIXEL_SIZE;
    public static Position cameraPosition;
    public static Position offsetPosition;

    public static void paintGraphics(Graphics g){
        //super.paint(g);
        //super.paintComponents(g);
        //g.clearRect(0, 0, WIDTH, HEIGHT);
        //g.setColor(Color.BLACK);
        //g.fillRect(0, 0, WIDTH, HEIGHT);

        paintTerrainChunk(g, EndlessTerrain.terrainChunksVisible);
        paintEntity(g, Spawn.entities);
        paintPlayer(g, Spawn.player);
        paintForegroundChunk(g, EndlessTerrain.terrainChunksVisible);
        paintUI(g, Spawn.player, Spawn.entities);
        debug(g, EndlessTerrain.terrainChunk, EndlessTerrain.terrainChunksVisible, Spawn.entities);
    }

    public static void debug(Graphics g, Map<Position, TerrainChunk> terrainChunk, ArrayList<TerrainChunk> terrainChunksVisible, ArrayList<Enemy> allEntities){
        
        if (Main.debugBoolDisplayChunk && terrainChunksVisible != null && terrainChunk != null) {
            g.setFont(new Font("Arial", Font.BOLD, 15));
            g.setColor(Color.ORANGE);
            g.drawString("Nombre de chunks total : " + terrainChunk.size(), 10, 50);
            g.drawString("Nombre de chunks chargés : " + terrainChunksVisible.size() + " : ", 10, 65);
            g.setFont(new Font("Arial", Font.PLAIN, 15));
            for(int i = 0; i < terrainChunksVisible.size(); i++){
                g.drawString((i+1) + ". terrainChunk " + terrainChunksVisible.get(i).position, 40, 65 + 20 * (i+1));
            }
        }
        if(Main.debugBoolOutlineChunk && terrainChunksVisible != null){
            g.setColor(Color.CYAN);
            int size = 2;
            int CS = Main.chunkSize;
            for(int i = 0; i < terrainChunksVisible.size(); i++){
                g.fillRect(
                (terrainChunksVisible.get(i).position.getPositionX() - CS/2) * TILE_SIZE - cameraPosition.getPositionX() * TILE_SIZE + offsetPosition.getPositionX(),
                0, size, Main.HEIGHT);
                g.fillRect(
                (terrainChunksVisible.get(i).position.getPositionX() + CS/2) * TILE_SIZE - cameraPosition.getPositionX() * TILE_SIZE + offsetPosition.getPositionX(),
                0, size, Main.HEIGHT);
            }
            for(int i = 0; i < terrainChunksVisible.size(); i++){
                g.fillRect(0,
                (terrainChunksVisible.get(i).position.getPositionY() + CS/2) * TILE_SIZE - cameraPosition.getPositionY() * TILE_SIZE + offsetPosition.getPositionY(),
                Main.WIDTH, size);
                g.fillRect(0,
                (terrainChunksVisible.get(i).position.getPositionY() - CS/2) * TILE_SIZE - cameraPosition.getPositionY() * TILE_SIZE + offsetPosition.getPositionY(),
                Main.WIDTH, size);
            }
        }
        if (Main.debugBoolEnemy && allEntities != null) {
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
        g.setFont(new Font("Arial", Font.BOLD, (int)(Main.BLOCK_SIZE * 0.75)));
        placeStringCenter(g, player.getUsername() + " " + player.position, player.position.getPositionX(), player.position.getPositionY(), 0, TILE_SIZE/2);

        g.setFont(new Font("Arial", Font.PLAIN, (int)(Main.BLOCK_SIZE * 0.75)));
        g.setColor(Color.RED);
        for(int i = 0; i < allEntities.size();i++){
            Enemy entity = allEntities.get(i);
            placeStringCenter(g, entity.getName(), entity.position.getPositionX(), entity.position.getPositionY(), 0, TILE_SIZE/2);
        }
    }

    public static void placePixel(Graphics g, int x, int y){
        g.fillRect(
        (x * TILE_SIZE) - cameraPosition.getPositionX() * TILE_SIZE + offsetPosition.getPositionX(),
        (y * TILE_SIZE) - cameraPosition.getPositionY() * TILE_SIZE + offsetPosition.getPositionY(),
        TILE_SIZE,
        TILE_SIZE);
    }

    public static void placeString(Graphics g, String str, int x, int y, int offsetX, int offsetY){
        g.drawString(str,
        x * TILE_SIZE - cameraPosition.getPositionX() * TILE_SIZE + offsetPosition.getPositionX() + offsetX,
        y * TILE_SIZE - cameraPosition.getPositionY() * TILE_SIZE + offsetPosition.getPositionY() - offsetY);
    }
    
    public static void placeStringCenter(Graphics g, String str, int x, int y, int offsetX, int offsetY){
        int width = g.getFontMetrics().stringWidth(str);
        placeString(g, str, x, y, offsetX + TILE_SIZE/2 - width/2, offsetY);
    }

    public static void placeImage(Graphics g, Image img, int x, int y){
        g.drawImage(img,
        (x * TILE_SIZE) - cameraPosition.getPositionX() * TILE_SIZE + offsetPosition.getPositionX() - (img.getWidth(null) * Main.PIXEL_SIZE - TILE_SIZE),
        (y * TILE_SIZE) - cameraPosition.getPositionY() * TILE_SIZE + offsetPosition.getPositionY() - (img.getHeight(null) * Main.PIXEL_SIZE - TILE_SIZE),
        img.getWidth(null) * Main.PIXEL_SIZE, img.getHeight(null) * Main.PIXEL_SIZE, null);
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
                chunk.position.getPositionX() + (x - chunk.tilemaps[y].length / 2),
                chunk.position.getPositionY() + (y - chunk.tilemaps.length / 2));
            }
        }
        for (Map.Entry<Position, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 0)
                placeImage(g, f.getValue().getImage(), f.getKey().getPositionX(), f.getKey().getPositionY());
        }
    }

    public static void paintChunkForeground(Graphics g, TerrainChunk chunk){
        for (Map.Entry<Position, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 1)
                placeImage(g, f.getValue().getImage(), f.getKey().getPositionX(), f.getKey().getPositionY());
        }
    }
    
    public static void paintPlayer(Graphics g, Player player){
        placeImage(g, player.getImage(), player.position.getPositionX(), player.position.getPositionY());
    }

    public static void paintEntity(Graphics g, ArrayList<Enemy> allEntities){
        if (allEntities == null) return;
        for(int i = 0; i < allEntities.size();i++){
            Enemy entity = allEntities.get(i);
            if (Position.distance(entity.position, Spawn.player.position) < Main.entitiesVisibleViewDst){
            placeImage(g, entity.getImage(), entity.position.getPositionX(), entity.position.getPositionY());
            }
        }
    }
}
