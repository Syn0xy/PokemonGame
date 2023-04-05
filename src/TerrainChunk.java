package src;

import java.util.Random;

public class TerrainChunk {
    public Position position;
    public Position edgePosition;
    private Position viewerPosition;
    private int chunkSize;

    public Tile[][] tilemaps;
    public Tile[][] foremaps;
    private MapData mapData;

    private static Random random = new Random(0);

    public TerrainChunk(Position coord, int indexChunk, int chunkSize)
    {
        this.position = coord.multiply(chunkSize);
        this.viewerPosition = EndlessTerrain.viewerPosition;
        this.chunkSize = chunkSize;

        initChunk();
        connectedBlockChunk();
        //generateObject();
        updateTerrainChunk();
    }

    public void initChunk(){
        mapData = new MapData(position, chunkSize);
        tilemaps = new Tile[chunkSize][chunkSize];
        foremaps = new Tile[chunkSize][chunkSize];
        Biome currentBiome;
        for(int y = 0; y < mapData.noisemap.length; y++){
            for(int x = 0; x < mapData.noisemap[y].length; x++){
                currentBiome = mapData.getBiomeByPosition(x, y);
                tilemaps[y][x] = new Tile(getBlockByValue(mapData.noisemap[y][x]), currentBiome);
                foremaps[y][x] = new Tile(Block.NOTHING, currentBiome);
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
                if(foremaps[y][x].getBlock() != Block.NOTHING) continue;
                Block block = tilemaps[y][x].getBlock();
                if(block == Block.LAND){
                    if(random.nextDouble() > 0.9){
                        block = random.nextDouble() > 0.2 ? Block.GRASS : Block.FLOWER;
                    }
                    else if (random.nextDouble() > 0.997){
                        block = Block.ROCK;
                    }
                    else if (random.nextDouble() > 0.997){
                        block = Block.TREE;
                    }
                }
                if(block == Block.WATER){
                    if(getBlock(x-1, y) == Block.LAND && getBlock(x, y-1) == Block.LAND) block = Block.WATER_CLIFF_1;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x, y-1) == Block.LAND && getBlock(x+1, y) == Block.WATER) block = Block.WATER_CLIFF_2;
                    else if(getBlock(x+1, y) == Block.LAND && getBlock(x, y-1) == Block.LAND) block = Block.WATER_CLIFF_3;
                    else if(getBlock(x, y-1) == Block.WATER && getBlock(x-1, y) == Block.LAND && getBlock(x, y+1) == Block.WATER) block = Block.WATER_CLIFF_4;
                    else if(getBlock(x, y-1) == Block.WATER && getBlock(x+1, y) == Block.LAND && getBlock(x, y+1) == Block.WATER) block = Block.WATER_CLIFF_6;
                    else if(getBlock(x-1, y) == Block.LAND && getBlock(x, y+1) == Block.LAND) block = Block.WATER_CLIFF_7;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x, y+1) == Block.LAND && getBlock(x+1, y) == Block.WATER) block = Block.WATER_CLIFF_8;
                    else if(getBlock(x+1, y) == Block.LAND && getBlock(x, y+1) == Block.LAND) block = Block.WATER_CLIFF_9;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x-1, y-1) == Block.LAND && getBlock(x, y-1) == Block.WATER) block = Block.WATER_CLIFF_10;
                    else if(getBlock(x+1, y) == Block.WATER && getBlock(x+1, y-1) == Block.LAND && getBlock(x, y-1) == Block.WATER) block = Block.WATER_CLIFF_11;
                    else if(getBlock(x-1, y) == Block.WATER && getBlock(x-1, y+1) == Block.LAND && getBlock(x, y+1) == Block.WATER) block = Block.WATER_CLIFF_12;
                    else if(getBlock(x+1, y) == Block.WATER && getBlock(x+1, y+1) == Block.LAND && getBlock(x, y+1) == Block.WATER) block = Block.WATER_CLIFF_13;
                    else if(random.nextDouble() > 0.998) block = Block.WATER_ROCK;
                }
                setBlockForemaps(block, x, y);
            }
        }
    }

    public void setBlockForemaps(Block block, int pX, int pY){
        Tile tile = foremaps[pY][pX];
        tile.setBlock(block);
        for(int y = pY - tile.getHeight() + 2; y <= pY; y++){
            if (y < foremaps.length && y >= 0)
                for(int x = pX - tile.getWidth() + 1; x <= pX; x++){
                    if (x < foremaps[y].length && x >= 0 && (x != pX || y != pY)){
                        foremaps[y][x].setBlock(Block.BLOCKED);
                    }
            }
        }
    }

    public void generateObject(){
        for(int y = 0; y < tilemaps.length; y++){
            for(int x = 0; x < tilemaps[y].length; x++){
                if(tilemaps[y][x].getBlock() == Block.LAND && random.nextDouble() > 0.998){
                    Spawn.spawnEntity(getPostionX(x), getPostionY(y));
                }
            }
        }
    }

    public int getPostionX(int x){ return position.getPositionX() - chunkSize/2 + x; }
    public int getPostionY(int y){ return position.getPositionY() - chunkSize/2 + y; }

    public Block getBlock(int x, int y){
        return getBlockByValue(mapData.getValueByPosition(x, y));
    }

    public Block getBlockInPosition(int x, int y){
        int posX = x - position.getPositionX() + chunkSize/2;
        int posY = y - position.getPositionY() - chunkSize/2;
        Block tile = tilemaps[tilemaps.length + posY][posX].getBlock();
        Block fore = foremaps[foremaps.length + posY][posX].getBlock();
        return fore.height >= tile.height ? fore : tile;
    }

    public void updateTerrainChunk()
    {
        if (Position.distance(position, viewerPosition) <= Main.maxViewDst){
            EndlessTerrain.terrainChunksVisible.add(this);
        }
    }
}