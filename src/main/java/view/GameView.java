package view;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import entity.Direction;
import entity.Spawn;
import model.GameScene;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {
    
    private static final int WIDTH = 1280;
    
    private static final int HEIGHT = 720;

    private static final String TITLE = "Pokemon Game RPG";

    private GameScene gameScene;

    public GameView(GameScene gameScene){
        super(WIDTH, HEIGHT);
        gameScene.attach(this);
        init();
    }
    
    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    protected void init() {
        add(new GameCanvas());
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if (key == KeyEvent.VK_LEFT) { Spawn.player.movePlayer(Direction.LEFT); }
                if (key == KeyEvent.VK_RIGHT) { Spawn.player.movePlayer(Direction.RIGHT); }
                if (key == KeyEvent.VK_UP) { Spawn.player.movePlayer(Direction.UP); }
                if (key == KeyEvent.VK_DOWN) { Spawn.player.movePlayer(Direction.DOWN);}

                // if (key == KeyEvent.VK_P) { debugDiplayChunk(); }
                // if (key == KeyEvent.VK_L) { debugEnemy(); }
                // if (key == KeyEvent.VK_O) { debugOutlineChunk(); }
            }
        });
    }

    @Override
    public void update(Subject subj) {
        repaint();
        refreshFrames();
    }

    @Override
    public void update(Subject subj, Object data) {

    }
}
