package core;

import java.util.Objects;

public class Point {


    public static final Point ZERO = new Point(0,0);

    private int x,y;

    public Point(int x,int y){
        this.x = x;
        this.y = y;
    }

    public Point translate(int dx, int dy){
        return new Point(getX()+dx,getY()+dy);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return getX() == point.getX() &&
                getY() == point.getY();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY());
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
