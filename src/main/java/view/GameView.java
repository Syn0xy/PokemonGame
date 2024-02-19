package view;


import model.GameScene;
import model.input.Input;
import view.scene.GameCanvas;
import view.scene.LoadingScene;
import view.util.Observer;
import view.util.Subject;

public class GameView extends View implements Observer {
    
    private static final int WIDTH = (int)(SCREEN_WIDTH * (2.0 / 3.0));
    
    private static final int HEIGHT = (int)(SCREEN_HEIGHT * (2.0 / 3.0));

    private static final String TITLE = "Pokemon Game RPG";

    public GameView(){
        super(WIDTH, HEIGHT);
        init();
    }
    
    @Override
    public String getTitle() {
        return TITLE;
    }

    @Override
    protected void init() {
        addKeyListener(Input.getInstance());
        setScene(new LoadingScene());
        // GameScene gameScene = new GameScene();
        // setScene(new GameCanvas(gameScene));
        // gameScene.attach(this);
        // gameScene.start();
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
