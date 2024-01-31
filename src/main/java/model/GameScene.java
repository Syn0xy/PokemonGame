package model;
import static view.GameCanvas.debugBoolDisplayChunk;
import static view.GameCanvas.debugBoolEnemy;
import static view.GameCanvas.debugBoolOutlineChunk;

import entity.Spawn;
import terrain.EndlessTerrain;
import view.GameCanvas;
import view.util.Subject;

public class GameScene extends Subject {

    private static final boolean LIMIT_FRAMES_PER_SECOND = true;
    
    private static final int FRAMES_PER_SECOND = 10;
    
    private EndlessTerrain endlessTerrain;
    
    public GameScene() {
        GameCanvas.getAllImage("./res/img/diamond_pearl/static/");
        Spawn.setPokemonBase("./res/pokemon/");
        Spawn.spawnPlayer("test", -32, -12);
        GameCanvas.cameraPosition = Spawn.player.position;
        endlessTerrain = new EndlessTerrain(Spawn.player.position);
    }

    public void start() {
        double prevTime = 0.0;
        double crntTime = 0.0;
        double maxTime = 1000.0 / FRAMES_PER_SECOND;
        while(true){
            if(LIMIT_FRAMES_PER_SECOND){
                crntTime = System.currentTimeMillis();
                if(crntTime - prevTime > maxTime){
                    prevTime = crntTime;
                    update();
                }
            }else{
                update();
            }
        }
    }

    private void update(){
        endlessTerrain.updateChunk();
        Spawn.updateEntities();
        notifyObservers();
    }
    
    public static void debugDiplayChunk(){ debugBoolDisplayChunk = !debugBoolDisplayChunk; }
    public static void debugEnemy(){ debugBoolEnemy = !debugBoolEnemy; }
    public static void debugOutlineChunk(){ debugBoolOutlineChunk = !debugBoolOutlineChunk; }
}