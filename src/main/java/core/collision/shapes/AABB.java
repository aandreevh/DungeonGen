package core.collision.shapes;


import core.collision.ICollideable;
import core.collision.UndeterminedCollisionDetection;
import core.math.unit.INumberUnit;

public class AABB<T extends INumberUnit>  implements IShape<T> {

    private  T x,y,width,height;

    public AABB(T x,T y,T width,T height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public AABB(float x,float y){

    }


    @Override
    public AABB offset(T x, T y) {
        return new AABB(getX().add(x),getY().add(y),getWidth(),getHeight());
    }

    @Override
    public boolean collides(ICollideable other) {
        if(other instanceof AABB){
            AABB aabb = (AABB)other;
            if(getX().compare(aabb.getX().add(aabb.getWidth()))<=0 &&
                    getX().add(getWidth()).compare( aabb.getX()) >=0 &&
                    getY().compare(aabb.getY().add( aabb.getHeight()))<=0 &&
                    getY().add( getHeight()).compare( aabb.getY()) >=0)
            {
                return true;
            }
        }else throw new UndeterminedCollisionDetection();

        return false;
    }

    public T getX() {
        return x;
    }

    public T getY() {
        return y;
    }

    public T getWidth() {
        return width;
    }

    public T getHeight() {
        return height;
    }

}
