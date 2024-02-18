import model.GameScene;
import view.GameView;
import view.ImageManager;

public class Main {
    
    public static void main(String[] args) {
        ImageManager.init();
        GameScene gameScene = new GameScene();
        new GameView(gameScene);
        gameScene.start();
    }

}
