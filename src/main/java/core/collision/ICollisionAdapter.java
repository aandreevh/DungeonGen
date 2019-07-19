package core.collision;

public interface ICollisionAdapter<A extends ICollideable, B extends ICollideable> {

    boolean collides(A a, B b);
}
