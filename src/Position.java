package src;

import java.lang.Math;

public class Position {
    private int positionX;
    private int positionY;

    public Position(int positionX, int positionY){
        this.positionX = positionX;
        this.positionY = positionY;
    }

    public int getPositionX(){ return this.positionX; }
    public int getPositionY(){ return this.positionY; }
    public void setPositionX(int positionX){ this.positionX = positionX; }
    public void setPositionY(int positionY){ this.positionY = positionY; }
    public void addPositionX(int addX){ this.positionX += addX; }
    public void addPositionY(int addY){ this.positionY += addY; }

    public boolean equals(Position position){
        return this.positionX == position.getPositionX() && this.positionY == position.getPositionY();
    }

    public static double distance(Position p1, Position p2){
        double c1 = Math.pow(p1.getPositionX()-p2.getPositionX(), 2);
        double c2 = Math.pow(p1.getPositionY()-p2.getPositionY(), 2);
        return Math.sqrt(c1+c2);
    }

    public Position multiply(int multiplier){
        return new Position(positionX * multiplier, positionY * multiplier);
    }

    public String toString(){
        return "(x:" + positionX + ";y:" + positionY + ")";
    }
}