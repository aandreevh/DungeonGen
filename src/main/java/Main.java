import core.AABB;
import core.Point;
import core.Structure;

public class Main {


    public static void main(String... args){

        Structure s1 = new Structure(new AABB(new Point(-5,-5),10,10));
        Structure s2 = new Structure(new AABB(new Point(5,5),10,10));
        Structure s3 = new Structure(new AABB(new Point(15,15),10,10));
        s3.translate(15,15);
        Structure s = new Structure();
        s.attach(s1,s2,s3);
        s3.deattach();
       Structure sprime = new Structure();
       sprime.attach(s);
       s.translate(-1,2);
       s.attach(new Structure(new AABB(new Point(80,96),1,1)));
        System.out.println(s1.getParent().get().getParent().get());
    }
}
