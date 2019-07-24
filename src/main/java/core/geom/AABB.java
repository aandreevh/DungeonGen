package core.geom;

import java.util.Objects;

public class AABB {


    public static final AABB ZERO = new AABB(Point.ZERO,0,0);

    private int x,y,width,height;

    public AABB(Point p, int width,int height){
       this(p.getX(),p.getY(),width,height);
    }

    public AABB(int x,int y, int width,int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public static AABB fromPoints(Point a,Point b){
        return new AABB(new Point(Math.min(a.getX(),b.getX()),
                Math.min(a.getY(),b.getY())),
                Math.abs(a.getX()-b.getX()),
                Math.abs(a.getY()-b.getY()));
    }



    public AABB translate(Point p){ return translate(p.getX(),p.getY());}
    public AABB translate(int dx,int dy){
        return new AABB(new Point(getX()+dx,getY()+dy),getWidth(),getHeight());
    }

    public Point getMinPoint(){
        return new Point(getX(),getY());
    }

    public Point getMaxPoint(){
        return new Point(getX()+getWidth(),getY()+getHeight());
    }

    public boolean intersects(AABB other){
        if(width == 0 || height ==0) return false;

        return getX() <= other.getX() + other.getWidth()-1 &&
                getX() + getWidth()-1>= other.getX() &&
                getY() <= other.getY() + other.getHeight()-1 &&
                getY() + getHeight()-1 >= other.getY();
    }

    public AABB append(AABB other){

        Point minA = getMinPoint();
        Point minB = other.getMinPoint();
        Point maxA = getMaxPoint();
        Point maxB = other.getMaxPoint();

        return AABB.fromPoints(new Point(Math.min(minA.getX(),minB.getX()),Math.min(minA.getY(),minB.getY())),
                new Point(Math.max(maxA.getX(),maxB.getX()),Math.max(maxA.getY(),maxB.getY())));
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AABB aabb = (AABB) o;
        return getX() == aabb.getX() &&
                getY() == aabb.getY() &&
                getWidth() == aabb.getWidth() &&
                getHeight() == aabb.getHeight();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return "AABB{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
