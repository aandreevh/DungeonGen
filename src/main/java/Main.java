import core.geom.Point;
import core.struct.Structure;
import core.struct.gen.BasicMapStructureGenerator;
import core.struct.gen.BasicRoomGenerator;
import core.struct.StructureBuilder;
import core.struct.gen.StructureGenerator;
import core.struct.Structures;

import java.util.Random;
import java.util.stream.Collectors;

public class Main {


    public static void main(String... args) {

        Random rand = new Random(0);

        StructureGenerator generator = new BasicRoomGenerator(rand, 3,3,10,10);


        long t1 = System.currentTimeMillis();
        BasicMapStructureGenerator gen = new BasicMapStructureGenerator(rand,
                generator.stream().limit(100).collect(Collectors.toList()));


        Structure primeStruct = gen.generate();


        long t2 = System.currentTimeMillis();

        System.out.println(primeStruct);
        System.out.println("Timeout: "+(t2-t1));
}

}
