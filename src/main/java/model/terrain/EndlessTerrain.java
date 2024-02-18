package model.terrain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.util.Vector2;

import java.lang.Math;

public class EndlessTerrain {
    
    //EndlessTerrain
    public final static int maxViewDst = 40;
    public final static int chunkSize = 16;
    public final static double noiseSize = 0.005;
    public final static double biomeSize = 0.01;
    
    public static float viewerMoveTresholdForChunkUpdate;
    private static int chunksVisibleInViewDst;
    public static Vector2 viewerPosition;

    private Vector2 viewerPositionOld;
    
    public static Map<Vector2, TerrainChunk> terrainChunk;
    public static List<TerrainChunk> terrainChunksVisible;

    public EndlessTerrain(Vector2 viewerPosition){
        EndlessTerrain.viewerPosition = viewerPosition;
        viewerPositionOld = new Vector2(viewerPosition.x, viewerPosition.y);
        terrainChunk = new HashMap<Vector2, TerrainChunk>();
        terrainChunksVisible = new ArrayList<TerrainChunk>();

        chunksVisibleInViewDst = Math.round(maxViewDst / chunkSize);
        viewerMoveTresholdForChunkUpdate = (int)(Math.sqrt(chunkSize * chunkSize * 2))/4;

        updateVisibleChunks();
    }

    public static Block getBlock(int x, int y){
        Vector2 pos;
        for(int i = 0; i < terrainChunksVisible.size(); i++){
            pos = terrainChunksVisible.get(i).position;
            if(x >= pos.x - chunkSize/2 &&
            x < pos.x + chunkSize/2 &&
            y >= pos.y - chunkSize/2 &&
            y < pos.y + chunkSize/2){
                return terrainChunksVisible.get(i).getBlockInPosition(x, y);
            }
        }
        return Block.NOTHING;
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

                if(terrainChunk.containsKey(viewedChunkCoord)){
                    terrainChunk.get(viewedChunkCoord).updateTerrainChunk();
                }
                else{
                    terrainChunk.put(viewedChunkCoord, new TerrainChunk(viewedChunkCoord, terrainChunk.size(), chunkSize));
                }
            }
        }
    }

    public void unvisibleAllTerrainChunk(){
        terrainChunksVisible.clear();
    }
}