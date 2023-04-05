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
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + positionX;
        result = prime * result + positionY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Position other = (Position) obj;
        if (positionX != other.positionX)
            return false;
        if (positionY != other.positionY)
            return false;
        return true;
    }
}