package model.terrain;

public enum Block {
    NOTHING(0, 0),
    BLOCKED(2, 0),
    GRASS(1, 0),
    FLOWER(1, 0),
    TREE(2, 1),
    LAND(1, 0),
    ROCK(2, 0),
    WATER(2, 0),
    WATER_ROCK(2, 0),
    WATER_CLIFF_1(2, 0),
    WATER_CLIFF_2(2, 0),
    WATER_CLIFF_3(2, 0),
    WATER_CLIFF_4(2, 0),
    WATER_CLIFF_6(2, 0),
    WATER_CLIFF_7(2, 0),
    WATER_CLIFF_8(2, 0),
    WATER_CLIFF_9(2, 0),
    WATER_CLIFF_10(2, 0),
    WATER_CLIFF_11(2, 0),
    WATER_CLIFF_12(2, 0),
    WATER_CLIFF_13(2, 0);

    public int height;
    public int plane;
    
    private Block(int height, int plane){
        this.height = height;
        this.plane = plane;
    }
}