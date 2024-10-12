package model.terrain;

import static model.terrain.EndlessTerrain.chunkSize;
import static model.terrain.EndlessTerrain.maxViewDst;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import model.entity.Entity;
import model.entity.PokemonEntity;
import model.entity.PokemonGenerator;
import utils.Vector2;
import view.Tile;

public class TerrainChunk {
    
    private static Random random = new Random(0);
    
    public Vector2 position;
    
    public Tile[][] tilemaps;
    
    private MapData mapData;
    
    public Map<Vector2, Tile> foremaps = new HashMap<Vector2, Tile>();

    private List<Entity> entities;

    public TerrainChunk(Vector2 coord, List<Entity> entities){
        this.position = coord.multiply(chunkSize);
        this.entities = entities;

        initChunk();
        connectedBlockChunk();
        generateObjects();
    }

    public void initChunk(){
        mapData = new MapData(position, chunkSize);
        tilemaps = new Tile[chunkSize][chunkSize];
        Biome currentBiome;
        for(int y = 0; y < mapData.noisemap.length; y++){
            for(int x = 0; x < mapData.noisemap[y].length; x++){
                currentBiome = mapData.getBiomeByLocalPosition(x, y);
                tilemaps[y][x] = new Tile(getBlockByValue(mapData.noisemap[y][x]), currentBiome);
            }
        }
    }

    public Block getBlockByValue(double value){
        return value > 0 ? Block.LAND : Block.WATER;
    }

    public boolean isVisible(Vector2 viewerPosition) {
        return position.distance(viewerPosition) <= maxViewDst;
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
        int rx = getPostionX(localX);
        int ry = getPostionY(localY);
        setBlockForemaps(block, rx, ry);
        // Tile tile = foremaps.get(new Vector2(rx, ry));
        // for(int y = ry - tile.getHeight() + 2; y <= ry; y++){
        //     for(int x = rx - tile.getWidth() + 1; x <= rx; x++){
        //         if (x != rx || y != ry){
        //                 setBlockForemaps(Block.ROCK, x, y);
        //         }
        //     }
        // }
    }

    public void setBlockForemaps(Block block, int x, int y){
        Vector2 p = new Vector2(x, y);
        if(foremaps.containsKey(p)){
            foremaps.get(p).setBlock(block);
        }else{
            foremaps.put(p, new Tile(block, MapData.getBiomeByPosition(x, y)));
        }
    }

    public void generateObjects(){
        for(int y = 0; y < tilemaps.length; y++){
            for(int x = 0; x < tilemaps[y].length; x++){
                if(tilemaps[y][x].getBlock() == Block.LAND && random.nextDouble() > 0.99){
                    this.entities.add(new PokemonEntity(
                        PokemonGenerator.getRandom(),
                        getPostionX(x),
                        getPostionY(y)
                    ));
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
}