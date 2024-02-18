package model.terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.entity.Entity;
import model.util.Vector2;

import java.lang.Math;

public class EndlessTerrain {
    
    public final static int maxViewDst = 40;
    public final static int chunkSize = 16;
    public final static double noiseSize = 0.005;
    public final static double biomeSize = 0.01;
    
    private double viewerMoveTresholdForChunkUpdate;
    private int chunksVisibleInViewDst;
    private Vector2 viewerPosition;

    private Vector2 viewerPositionOld;
    
    private Map<Vector2, TerrainChunk> terrainChunks;
    private List<TerrainChunk> terrainChunksVisible;
    private List<Entity> entities;

    public EndlessTerrain(Vector2 viewerPosition, List<Entity> entities){
        this.viewerPosition = viewerPosition;
        this.viewerPositionOld = new Vector2(viewerPosition.x, viewerPosition.y);
        this.entities = entities;
        terrainChunks = new HashMap<Vector2, TerrainChunk>();
        terrainChunksVisible = new ArrayList<TerrainChunk>();

        chunksVisibleInViewDst = Math.round(maxViewDst / chunkSize);
        viewerMoveTresholdForChunkUpdate = (int)(Math.sqrt(chunkSize * chunkSize * 2))/4;

        updateVisibleChunks();
    }

    public Map<Vector2, TerrainChunk> getTerrainChunks() {
        return terrainChunks;
    }

    public List<TerrainChunk> getTerrainChunksVisible() {
        return terrainChunksVisible;
    }

    public void updateChunk(){
        if (viewerPositionOld.distance(viewerPosition) > viewerMoveTresholdForChunkUpdate) {
            viewerPositionOld = new Vector2(viewerPosition.x, viewerPosition.y);
            updateVisibleChunks();
        }
    }

    private void updateVisibleChunks(){
        unvisibleAllTerrainChunk();

        int currentChunkCoordX = Math.round(viewerPosition.x / chunkSize);
        int currentChunkCoordY = Math.round(viewerPosition.y / chunkSize);
        
        for (int yOffset = -chunksVisibleInViewDst; yOffset <= chunksVisibleInViewDst; yOffset++){
            for (int xOffset = -chunksVisibleInViewDst; xOffset <= chunksVisibleInViewDst; xOffset++){
                Vector2 viewedChunkCoord = new Vector2(currentChunkCoordX + xOffset, currentChunkCoordY + yOffset);

                if(!terrainChunks.containsKey(viewedChunkCoord)){
                    terrainChunks.put(viewedChunkCoord, new TerrainChunk(viewedChunkCoord, entities));
                }
                
                TerrainChunk chunk = terrainChunks.get(viewedChunkCoord);
                if(chunk.isVisible(viewerPosition)){
                    terrainChunksVisible.add(chunk);
                }
            }
        }
    }

    public void unvisibleAllTerrainChunk(){
        terrainChunksVisible.clear();
    }
}