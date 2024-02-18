package model.terrain;

import static model.terrain.EndlessTerrain.maxViewDst;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import model.entity.Spawn;
import model.util.Vector2;
import view.Tile;

public class TerrainChunk {
    public Vector2 position;
    public Vector2 edgePosition;
    private Vector2 viewerPosition;
    private int chunkSize;

    public Tile[][] tilemaps;
    
    public Map<Vector2, Tile> foremaps = new HashMap<Vector2, Tile>();

    private MapData mapData;

    private static Random random = new Random(0);

    public TerrainChunk(Vector2 coord, int indexChunk, int chunkSize){
        this.chunkSize = chunkSize;
        this.position = coord.multiply(chunkSize);
        this.viewerPosition = EndlessTerrain.viewerPosition;

        initChunk();
        connectedBlockChunk();
        generateObject();
        updateTerrainChunk();
    }

    public void initChunk(){
        mapData = new MapData(position, chunkSize);
        tilemaps = new Tile[chunkSize][chunkSize];
        Biome currentBiome;
        for(int y = 0; y < mapData.noisemap.length; y++){
            for(int x = 0; x < mapData.noisemap[y].length; x++){
                currentBiome = mapData.getBiomeByLocalPosition(x, y);
                tilemaps[y][x] = new Tile(getBlockByValue(mapData.noisemap[y][x]), currentBiome);
                //foremaps[y][x] = new Tile(Block.NOTHING, currentBiome);
            }
        }
    }

    public Block getBlockByValue(double value){
        Block block = Block.NOTHING;
        if(value > 0){
            block = Block.LAND;
        }else{
            block = Block.WATER;
        }
        return block;
    }

    public void connectedBlockChunk(){
        for(int y = 0; y < tilemaps.length; y++){
            for(int x = 0; x < tilemaps[y].length; x++){
                Vector2 p = getPosition(x, y);
                if(foremaps.containsKey(p)) continue;
                
                Block backBlock = tilemaps[y][x].getBlock();
                Block newBlock = null;
                if(backBlock == Block.LAND){
                    if(random.nextDouble() > 0.9){
                        newBlock = random.nextDouble() > 0.2 ? Block.GRASS : Block.FLOWER;
                    }
                    else if (random.nextDouble() > 0.997){
                        newBlock = Block.ROCK;
                    }
                    else if (random.nextDouble() > 0.997){
                        newBlock = Block.TREE;
                    }
                }
                else if(backBlock == Block.WATER){
                    if(getBlock(x-1, y) == Block.LAND && getBlock(x, y-1) == Block.LAND) newBlock = Block.WATER_CLIFF_1;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x, y-1) == Block.LAND && getBlock(x+1, y) == Block.WATER) newBlock = Block.WATER_CLIFF_2;
                    else if(getBlock(x+1, y) == Block.LAND && getBlock(x, y-1) == Block.LAND) newBlock = Block.WATER_CLIFF_3;
                    else if(getBlock(x, y-1) == Block.WATER && getBlock(x-1, y) == Block.LAND && getBlock(x, y+1) == Block.WATER) newBlock = Block.WATER_CLIFF_4;
                    else if(getBlock(x, y-1) == Block.WATER && getBlock(x+1, y) == Block.LAND && getBlock(x, y+1) == Block.WATER) newBlock = Block.WATER_CLIFF_6;
                    else if(getBlock(x-1, y) == Block.LAND && getBlock(x, y+1) == Block.LAND) newBlock = Block.WATER_CLIFF_7;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x, y+1) == Block.LAND && getBlock(x+1, y) == Block.WATER) newBlock = Block.WATER_CLIFF_8;
                    else if(getBlock(x+1, y) == Block.LAND && getBlock(x, y+1) == Block.LAND) newBlock = Block.WATER_CLIFF_9;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x-1, y-1) == Block.LAND && getBlock(x, y-1) == Block.WATER) newBlock = Block.WATER_CLIFF_10;
                    else if(getBlock(x+1, y) == Block.WATER && getBlock(x+1, y-1) == Block.LAND && getBlock(x, y-1) == Block.WATER) newBlock = Block.WATER_CLIFF_11;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x-1, y+1) == Block.LAND && getBlock(x, y+1) == Block.WATER) newBlock = Block.WATER_CLIFF_12;
                    else if(getBlock(x+1, y) == Block.WATER && getBlock(x+1, y+1) == Block.LAND && getBlock(x, y+1) == Block.WATER) newBlock = Block.WATER_CLIFF_13;
                    else if(random.nextDouble() > 0.998) newBlock = Block.WATER_ROCK;
                }
                if (newBlock != null) setAdvancedBlockForemaps(newBlock, x, y);
            }
        }
    }

    public void setAdvancedBlockForemaps(Block block, int localX, int localY){
        int rX = getPostionX(localX);
        int rY = getPostionY(localY);
        setBlockForemaps(block, rX, rY);
        Tile tile = foremaps.get(new Vector2(rX, rY));
        for(int y = rY - tile.getHeight() + 2; y <= rY; y++){
            for(int x = rX - tile.getWidth() + 1; x <= rX; x++){
                if (x != rX || y != rY){
                        setBlockForemaps(Block.ROCK, x, y);
                }
            }
        }
    }

    public void setBlockForemaps(Block block, int x, int y){
        Vector2 p = new Vector2(x, y);
        if(foremaps.containsKey(p)){
            foremaps.get(p).setBlock(block);
        }else{
            foremaps.put(p, new Tile(block, MapData.getBiomeByPosition(x, y)));
        }
    }

    public void generateObject(){
        for(int y = 0; y < tilemaps.length; y++){
            for(int x = 0; x < tilemaps[y].length; x++){
                if(tilemaps[y][x].getBlock() == Block.LAND && random.nextDouble() > 0.99){
                    Spawn.spawnEntity(getPostionX(x), getPostionY(y));
                }
            }
        }
    }

    public int getPostionX(int x){ return position.x - chunkSize/2 + x; }
    public int getPostionY(int y){ return position.y - chunkSize/2 + y; }
    public Vector2 getPosition(int x, int y){ return new Vector2(getPostionX(x), getPostionY(y)); }

    public Block getBlock(int x, int y){
        return getBlockByValue(mapData.getValueByLocalPosition(x, y));
    }

    public Block getBlockInPosition(int x, int y){
        int posX = x - position.x + chunkSize/2;
        int posY = y - position.y - chunkSize/2;
        Block tile = tilemaps[tilemaps.length + posY][posX].getBlock();
        Block fore = null;
        Vector2 p = new Vector2(x, y);
        if(foremaps.containsKey(p)) fore = foremaps.get(p).getBlock();
        return fore == null ? tile : fore.height >= tile.height ? fore : tile;
    }

    public void updateTerrainChunk() {
        if (position.distance(viewerPosition) <= maxViewDst) {
            EndlessTerrain.terrainChunksVisible.add(this);
        }
    }
}