import core.AABB;
import core.Point;
import core.Structure;

import java.sql.Struct;
import java.time.Clock;
import java.util.Collections;
import java.util.Random;

public class Main {


    public static void main(String... args) {

        Random r = new Random(System.nanoTime());

        int minWH =5;
        int maxWH =10;
        int S = 1;
        int rooms = 1000;

        Structure primeStruct = new Structure();

        long elapsedTime  = System.nanoTime();

        //push init room
        primeStruct.attach(new Structure(new AABB(-5,-5,10,10)));

        for(int room =1;room < rooms;room++){
           Structure closeStruct= primeStruct.getSubStructures()
                   .get((r.nextInt()& Integer.MAX_VALUE)%primeStruct.getSubStructures().size());
           Point vec = new Point((r.nextInt()& Integer.MAX_VALUE)%(2*S+1) -S,
                   (r.nextInt()& Integer.MAX_VALUE)%(2*S+1)-S);
           Point coord = new Point((closeStruct.getAABB().getX()+closeStruct.getAABB().getWidth())/2,
                   (closeStruct.getAABB().getY()+closeStruct.getAABB().getHeight())/2);
           int width =minWH+ (r.nextInt()& Integer.MAX_VALUE)%(maxWH-minWH+1);
            int height =minWH+ (r.nextInt()& Integer.MAX_VALUE)%(maxWH-minWH+1);
           if(vec.equals(Point.ZERO)) vec= vec.translate(1,1);
           Structure nstruct = new Structure(new AABB(coord,width,height));

           final Point v = vec;
           primeStruct.whileIntersects(nstruct,(Structure s, AABB box)->{
             //  System.out.println(box);
               s.translate(v.getX(),v.getY());
           });

           primeStruct.attach(nstruct);
           System.out.println("Structure "+(room+1)+" added !");

        }

        elapsedTime = System.nanoTime() -elapsedTime;

        double seconds = (double)elapsedTime / 1_000_000_000.0;

        System.out.println("Milliseconds: "+seconds*1000);

    //    System.out.println(primeStruct);
        StringBuilder b = new StringBuilder();
        for(int xx=0;xx<primeStruct.getAABB().getHeight();xx++){
            for (int yy=0;yy<primeStruct.getAABB().getWidth();yy++){

                if(primeStruct.intersects(new AABB(primeStruct.getAABB().getX()+xx,
                        primeStruct.getAABB().getY()+yy,1,1))){
                    b.append("x");
                }else{
                   b.append(" ");
                }
            }
            b.append("\n");
        }
        System.out.println(b.toString());

}

}
