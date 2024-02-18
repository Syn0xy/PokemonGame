package model;

import model.entity.Player;
import model.entity.Spawn;
import model.terrain.EndlessTerrain;
import view.GameCanvas;
import view.util.Subject;

public class GameScene extends Subject {

    private static final boolean LIMIT_FRAMES_PER_SECOND = true;
    
    private static final int FRAMES_PER_SECOND = 60;
    
    private EndlessTerrain endlessTerrain;

    private Player player;
    
    public GameScene() {
        this.player = Spawn.spawnPlayer("test", -32, -12);
        GameCanvas.getAllImage();
        endlessTerrain = new EndlessTerrain(player.position);
    }

    public Player getPlayer() {
        return player;
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
}