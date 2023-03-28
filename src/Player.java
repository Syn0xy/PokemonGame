package src;

public class Player extends Entity{
    private String username;

    public Player(String username, int positionX, int positionY){
        super(positionX, positionY);
        this.username = username;
        setImage("ressources/img/red.png");
    }

    public String getUsername(){ return this.username; }

    public void movePlayer(Direction direction){       
        if (direction == Direction.RIGHT) { // DROITE
            if (nextMove(position.getPositionX()+1, position.getPositionY())) position.addPositionX(1);
        }
        if (direction == Direction.LEFT) { // GAUCHE
            if (nextMove(position.getPositionX()-1, position.getPositionY())) position.addPositionX(-1);
        }
        if (direction == Direction.UP) { // HAUT
            if (nextMove(position.getPositionX(), position.getPositionY()-1)) position.addPositionY(-1);
        }
        if (direction == Direction.DOWN) { // BAS
            if (nextMove(position.getPositionX(), position.getPositionY()+1)) position.addPositionY(1);
        }
    }

    public boolean nextMove(int x, int y){
        return EndlessTerrain.getBlock(x, y).height != 2;
    }
}