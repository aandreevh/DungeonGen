import core.collision.shapes.AABB;

public class Main {


    public static void main(String... args){
        AABB a1 = new AABB(0,0,100,100);
        AABB a2 = new AABB(25,25,25,25);

        System.out.println(a1.collides(a2.offset(75,75)));
    }
}
