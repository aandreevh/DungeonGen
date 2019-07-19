package core.collision.shapes;

import core.collision.ICollideable;
import core.collision.IMovable;
import core.math.unit.INumberUnit;

public class Point<T extends INumberUnit> implements IShape<T> {
    T x, y;

    public Point(T x,T y){
        this.x = x;
        this.y = y;
    }

    public T getX(){
        return x;
    }

    public T getY(){
        return y;
    }

    @Override
    public Point offset(T x, T y) {
        return new Point(getX().add(x),getY().add(y));
    }

    @Override
    public boolean collides(ICollideable other) {
        return false;
    }

}
