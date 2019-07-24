package core.struct.gen;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Random;
import java.util.stream.Stream;

public abstract class StructureGenerator {

    private Random rand ;

    public StructureGenerator(Random rand){
        this.rand = rand;
    }

    public Random getRandom(){
        return rand;
    }


    public abstract Structure generate();

    public Stream<Structure> stream(){
        return Stream.generate(this::generate);
    }
}
