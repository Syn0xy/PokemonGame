package src;

public class MapData {
    private Position position;
    public double[][] noisemap;
    public double[][] biomemap;

    public MapData (Position position, int chunkSize){
        this.position = position;
        this.noisemap = new double[chunkSize][chunkSize];
        this.biomemap = new double[chunkSize][chunkSize];
        initNoiseMap();
        initBiomeMap();
    }
    
    public void initNoiseMap(){
        for(int y = 0; y < noisemap.length; y++)
            for(int x = 0; x < noisemap[y].length; x++)
            	noisemap[y][x] = getValueByPosition(x, y);
    }
    
    public void initBiomeMap(){
        for(int y = 0; y < biomemap.length; y++)
            for(int x = 0; x < biomemap[y].length; x++){
            	biomemap[y][x] = getValueByPosition(x, y, Main.biomeSize);
            }
    }

    public double getValueByPosition(int x, int y, double size){
        return SimplexNoise.noise((x + position.getPositionX()) * size, (y + position.getPositionY()) * size);
    }

    public double getValueByPosition(int x, int y){
        return getValueByPosition(x, y, Main.noiseSize);
    }

    public Biome getBiomeByPosition(int x, int y){
        double currentValue = getValueByPosition(x, y, Main.biomeSize);
        int index = Math.round((float)(((currentValue+1)*(Biome.values().length-1))/2));
        return Biome.values()[index];
    }
}