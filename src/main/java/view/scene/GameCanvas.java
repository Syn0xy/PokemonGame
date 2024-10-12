package view.scene;

import static model.terrain.EndlessTerrain.chunkSize;
import static model.terrain.EndlessTerrain.maxViewDst;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import model.GameScene;
import model.entity.Entity;
import model.entity.Player;
import model.terrain.EndlessTerrain;
import model.terrain.TerrainChunk;
import utils.Vector2;
import view.Tile;

public class GameCanvas extends Scene {
    
    public final static int BLOCK_SIZE = 16;
    public final static int PIXEL_SIZE = 2;
    public final static int entitiesVisibleViewDst = (int)(maxViewDst / 1.5);
    public final static int TILE_SIZE = BLOCK_SIZE * PIXEL_SIZE;

    public static boolean debugBoolDisplayChunk = false;
    public static boolean debugBoolEnemy = false;
    public static boolean debugBoolOutlineChunk = false;
    
    private final List<Entity> entities;

    private final EndlessTerrain terrain;

    private final Player player;

    private final Vector2 position;

    public GameCanvas(final GameScene gameScene){
        this.entities = gameScene.getEntities();
        this.terrain = gameScene.getEndlessTerrain();
        this.player = gameScene.getPlayer();
        this.position = player.position;
    }

    @Override
    public void start() {}

    @Override
    public void paint(final Graphics g) {
        final Collection<TerrainChunk> chunks = this.terrain.getVisibleChunks();

        clearScreen(g);
        g.translate(getWidth() / 2, getHeight() / 2);
        paintTerrainChunk(g, chunks);
        drawEntities(g);
        paintForegroundChunk(g, chunks);
        paintUI(g);
        g.translate(- getWidth() / 2, - getHeight() / 2);
        debug(g);
    }

    private void clearScreen(final Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, getWidth(), getHeight());
    }

    private void debug(final Graphics g){
        final Collection<TerrainChunk> chunks = this.terrain.getChunks().values();
        final Collection<TerrainChunk> visibleChunks = this.terrain.getVisibleChunks();
        
        if(debugBoolOutlineChunk){
            g.setColor(Color.CYAN);
            final int size = 2;
            final int cs = chunkSize;

            for (final TerrainChunk chunk : visibleChunks) {
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
            for (final TerrainChunk chunk : visibleChunks) {
                g.drawString(i + ". terrainChunk " + chunk.position, 40, 65 + 20 * i);
                i++;
            }
        }
        if (debugBoolEnemy && entities != null) {
            g.drawString("Nombre de d'entités total : " + entities.size(), 300, 65);
            int i = 1;
            for (final Entity entity : entities) {
                g.drawString(i + ". entité " + entity.position, 340, 70 + 15 * i);
                i++;
            }
        }
    }

    private void paintUI(final Graphics g){
        g.setColor(Color.RED);
        g.setFont(new Font("Arial", Font.PLAIN, (int)(BLOCK_SIZE * 0.75)));
        
        for (final Entity entity : entities) {
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

    private void placeString(final Graphics g, final String str, final int x, final int y, final int offsetX, final int offsetY){
        g.drawString(str,
        x * TILE_SIZE - position.x * TILE_SIZE + offsetX,
        y * TILE_SIZE - position.y * TILE_SIZE - offsetY);
    }
    
    private void placeStringCenter(final Graphics g, final String str, final int x, final int y, final int offsetX, final int offsetY){
        final int width = g.getFontMetrics().stringWidth(str);
        placeString(g, str, x, y, offsetX + TILE_SIZE/2 - width/2, offsetY);
    }

    private void placeImage(final Graphics g, final Image img, final int x, final int y){
        g.drawImage(img,
        (x * TILE_SIZE) - position.x * TILE_SIZE - (img.getWidth(null) * PIXEL_SIZE - TILE_SIZE),
        (y * TILE_SIZE) - position.y * TILE_SIZE - (img.getHeight(null) * PIXEL_SIZE - TILE_SIZE),
        img.getWidth(null) * PIXEL_SIZE, img.getHeight(null) * PIXEL_SIZE, null);
    }
    
    private void paintTerrainChunk(final Graphics g, final Collection<TerrainChunk> chunks) {
        chunks.forEach(chunk -> this.paintChunkBackground(g, chunk));
    }
    
    private void paintForegroundChunk(final Graphics g, final Collection<TerrainChunk> chunks) {
        chunks.forEach(chunk -> this.paintChunkForeground(g, chunk));
    }

    private void paintChunkBackground(final Graphics g, final TerrainChunk chunk){
        for(int y = 0; y < chunk.tilemaps.length; y++){
            for(int x = 0; x < chunk.tilemaps[y].length; x++){
                placeImage(g, chunk.tilemaps[y][x].getImage(),
                chunk.position.x + (x - chunk.tilemaps[y].length / 2),
                chunk.position.y + (y - chunk.tilemaps.length / 2));
            }
        }
        for (final Map.Entry<Vector2, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 0)
                placeImage(g, f.getValue().getImage(), f.getKey().x, f.getKey().y);
        }
    }

    private void paintChunkForeground(final Graphics g, final TerrainChunk chunk){
        for (final Map.Entry<Vector2, Tile> f : chunk.foremaps.entrySet()) {
            if(f.getValue().getBlock().plane == 1)
                placeImage(g, f.getValue().getImage(), f.getKey().x, f.getKey().y);
        }
    }

    private void drawEntities(final Graphics g){
        this.entities.forEach(entity -> this.drawEntity(g, entity));
    }

    private void drawEntity(final Graphics g, final Entity entity) {
        if (entity.position.distance(player.position) >= entitiesVisibleViewDst) {
            return;
        }
        
        this.placeImage(
            g,
            entity.getImage(),
            entity.position.x,
            entity.position.y
        );
    }
}
