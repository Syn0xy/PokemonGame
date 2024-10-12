package utils;

public class Vector2 {
    
    public int x;
    
    public int y;
    
    public Vector2(final int x, final int y) {
        this.x = x;
        this.y = y;
    }
    
    public double distance(final Vector2 vec2) {
        final int dx = vec2.x - this.x;
        final int dy = vec2.y - this.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    
    public Vector2 multiply(final int m) {
        return new Vector2(
            this.x * m,
            this.y * m
        );
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
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final Vector2 other = (Vector2) obj;
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
