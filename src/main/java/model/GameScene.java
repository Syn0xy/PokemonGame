package model;

import java.util.ArrayList;
import java.util.List;

import model.entity.Entity;
import model.entity.Player;
import model.terrain.EndlessTerrain;
import view.util.Subject;

public class GameScene extends Subject {

    private static final boolean LIMIT_FRAMES_PER_SECOND = true;
    
    private static final int FRAMES_PER_SECOND = 60;
    
    public List<Entity> entities;
    
    private EndlessTerrain endlessTerrain;

    private Player player;
    
    public GameScene() {
        this.entities = new ArrayList<>();
        init();
        this.endlessTerrain = new EndlessTerrain(player.position, entities);
    }

    private void init(){
        this.player = new Player("test", -32, -12);
        entities.add(player);
    }

    public List<Entity> getEntities() {
        return entities;
    }

    public EndlessTerrain getEndlessTerrain() {
        return endlessTerrain;
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
        updateEntities();
        notifyObservers();
    }

    private void updateEntities(){
        for (Entity entity : entities) {
            entity.update();
        }
    }
}