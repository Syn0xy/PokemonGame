package src;

import java.util.ArrayList;

import java.lang.Math;

public class EndlessTerrain {
    public static float viewerMoveTresholdForChunkUpdate;
    private static int chunksVisibleInViewDst;
    public static Position viewerPosition;

    private Position viewerPositionOld;
    
    private ArrayList<Position> terrainChunkPosition;
    public static ArrayList<TerrainChunk> terrainChunk;
    public static ArrayList<TerrainChunk> terrainChunksVisible;

    public EndlessTerrain(Position viewerPosition){
        EndlessTerrain.viewerPosition = viewerPosition;
        viewerPositionOld = new Position(viewerPosition.getPositionX(), viewerPosition.getPositionY());
        terrainChunk = new ArrayList<TerrainChunk>();
        terrainChunkPosition = new ArrayList<Position>();
        terrainChunksVisible = new ArrayList<TerrainChunk>();

        chunksVisibleInViewDst = Math.round(Main.maxViewDst / Main.chunkSize);
        viewerMoveTresholdForChunkUpdate = (int)(Math.sqrt(Main.chunkSize * Main.chunkSize * 2))/4;

        updateVisibleChunks();
    }

    public static Block getBlock(int x, int y){
        Position pos;
        for(int i = 0; i < terrainChunksVisible.size(); i++){
            pos = terrainChunksVisible.get(i).position;
            if(x >= pos.getPositionX() - Main.chunkSize/2 &&
            x < pos.getPositionX() + Main.chunkSize/2 &&
            y >= pos.getPositionY() - Main.chunkSize/2 &&
            y < pos.getPositionY() + Main.chunkSize/2){
                return terrainChunksVisible.get(i).getBlockInPosition(x, y);
            }
        }
        return Block.NOTHING;
    }

    public void updateChunk(){
        if (Position.distance(viewerPosition, viewerPositionOld) > viewerMoveTresholdForChunkUpdate)
        {
            viewerPositionOld = new Position(viewerPosition.getPositionX(), viewerPosition.getPositionY());
            updateVisibleChunks();
        }
    }

    private void updateVisibleChunks(){
        unvisibleAllTerrainChunk();

        int currentChunkCoordX = Math.round(viewerPosition.getPositionX() / Main.chunkSize);
        int currentChunkCoordY = Math.round(viewerPosition.getPositionY() / Main.chunkSize);

        for (int yOffset = -chunksVisibleInViewDst; yOffset <= chunksVisibleInViewDst; yOffset++){
            for (int xOffset = -chunksVisibleInViewDst; xOffset <= chunksVisibleInViewDst; xOffset++){
                Position viewedChunkCoord = new Position(currentChunkCoordX + xOffset, currentChunkCoordY + yOffset);

                if (containsChunkPosition(viewedChunkCoord)){
                    updateTerrainChunk(viewedChunkCoord);
                }
                else{
                    terrainChunk.add(new TerrainChunk(viewedChunkCoord, terrainChunk.size(), Main.chunkSize));
                    terrainChunkPosition.add(viewedChunkCoord);
                }
            }
        }
    }

    public boolean containsChunkPosition(Position position){
        for(Position pos : terrainChunkPosition)
            if (pos.equals(position))
                return true;
        return false;
    }

    public void unvisibleAllTerrainChunk(){
        terrainChunksVisible.clear();
    }

    public void updateTerrainChunk(Position position){
        for(int i = 0; i < terrainChunk.size(); i++){
            if(terrainChunkPosition.get(i).equals(position)){
                terrainChunk.get(i).updateTerrainChunk();
            }
        }
    }
}