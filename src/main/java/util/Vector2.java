package util;

public class Vector2 {
    
    public int x;
    
    public int y;
    
    public Vector2(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public double distance(Vector2 vec2){
        return Math.sqrt(
            Math.pow(vec2.x - x, 2) +
            Math.pow(vec2.y - y, 2)
        );
    }
    
    public Vector2 multiply(int m){
        return new Vector2(x * m, y * m);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
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
        Vector2 other = (Vector2) obj;
        if (x != other.x)
            return false;
        if (y != other.y)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Position [x=" + x + ", y=" + y + "]";
    }

}
