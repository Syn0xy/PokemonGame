package view;

import static view.GameCanvas.debugBoolDisplayChunk;
import static view.GameCanvas.debugBoolEnemy;
import static view.GameCanvas.debugBoolOutlineChunk;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import model.GameScene;
import model.entity.Direction;
import model.entity.Player;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {
    
    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));
    
    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));

    private static final String TITLE = "Pokemon Game RPG";

    private GameScene gameScene;

    public GameView(GameScene gameScene){
        super(WIDTH, HEIGHT);
        this.gameScene = gameScene;
        gameScene.attach(this);
        init();
    }
    
    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    protected void init() {
        add(new GameCanvas(gameScene));
        Player player = gameScene.getPlayer();
        
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                
                if (key == KeyEvent.VK_LEFT) { player.movePlayer(Direction.LEFT); }
                if (key == KeyEvent.VK_RIGHT) { player.movePlayer(Direction.RIGHT); }
                if (key == KeyEvent.VK_UP) { player.movePlayer(Direction.UP); }
                if (key == KeyEvent.VK_DOWN) { player.movePlayer(Direction.DOWN);}

                if (key == KeyEvent.VK_P) { debugDiplayChunk(); }
                if (key == KeyEvent.VK_L) { debugEnemy(); }
                if (key == KeyEvent.VK_O) { debugOutlineChunk(); }
            }
        });
    }
    
    public static void debugDiplayChunk(){ debugBoolDisplayChunk = !debugBoolDisplayChunk; }
    public static void debugEnemy(){ debugBoolEnemy = !debugBoolEnemy; }
    public static void debugOutlineChunk(){ debugBoolOutlineChunk = !debugBoolOutlineChunk; }

    @Override
    public void update(Subject subj) {
        repaint();
        refreshFrames();
    }

    @Override
    public void update(Subject subj, Object data) {

    }
}
