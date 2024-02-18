package model.terrain;

import static model.terrain.EndlessTerrain.biomeSize;
import static model.terrain.EndlessTerrain.chunkSize;
import static model.terrain.EndlessTerrain.noiseSize;

import model.util.SimplexNoise;
import model.util.Vector2;;

public class MapData {
    private Vector2 position;
    public double[][] noisemap;
    public double[][] biomemap;

    public MapData (Vector2 position, int chunkSize){
        this.position = position;
        this.noisemap = new double[chunkSize][chunkSize];
        this.biomemap = new double[chunkSize][chunkSize];
        initNoiseMap();
        initBiomeMap();
    }
    
    public void initNoiseMap(){
        for(int y = 0; y < noisemap.length; y++)
            for(int x = 0; x < noisemap[y].length; x++)
            	noisemap[y][x] = getValueByLocalPosition(x, y);
    }
    
    public void initBiomeMap(){
        for(int y = 0; y < biomemap.length; y++)
            for(int x = 0; x < biomemap[y].length; x++){
            	biomemap[y][x] = getValueByLocalPosition(x, y, biomeSize);
            }
    }

    public double getValueByLocalPosition(int x, int y, double size){
        return getValueByPosition(x + position.x, y + position.y, size);
    }
    
    public static double getValueByPosition(int x, int y, double size){
        return SimplexNoise.noise(x * size, y * size);
    }

    public double getValueByLocalPosition(int x, int y){
        return getValueByLocalPosition(x, y, noiseSize);
    }

    public Biome getBiomeByLocalPosition(int x, int y){
        return getBiomeByPosition(x + position.x - chunkSize/2, y + position.y - chunkSize/2);
    }

    public static Biome getBiomeByPosition(int x, int y){
        double currentValue = getValueByPosition(x, y, biomeSize);
        int index = Math.round((float)(((currentValue+1)*(Biome.values().length-1))/2));
        return Biome.values()[index];
    }
}