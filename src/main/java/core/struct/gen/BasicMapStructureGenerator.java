package core.struct.gen;

import core.geom.Point;
import core.struct.Structure;
import core.struct.StructureBuilder;
import core.struct.Structures;

import java.util.Collection;
import java.util.Random;
import java.util.function.BiFunction;
import java.util.stream.Stream;

public class BasicMapStructureGenerator extends StructureGenerator {


    private Collection<Structure> structures;
    private BiFunction<Structure,Structure,Boolean> callback;
    private StructureBuilder builder;

    protected Collection<Structure> getStructures(){
        return this.structures;
    }


    public BasicMapStructureGenerator(Random rand, Collection<Structure> structs){
        this(rand,structs,(u,v)->true);
    }


    public BasicMapStructureGenerator(Random rand, Collection<Structure> structs,
                                      BiFunction<Structure, Structure, Boolean> callback){
        super(rand);
        this.structures = structs;
        this.callback = callback;
    }

    @Override
    public Structure generate() {
        if(builder !=null) throw new UnsupportedOperationException();

        builder = new StructureBuilder((w,s)->{
            Point p = Point.ZERO;

            if(w.getSubStructures().size()>0)
                w.getSubStructures().get(getRandom().nextInt(w.getSubStructures().size())).getAABB().getMaxPoint();

            s.translate(p);

            final Point vec = Point.DIRECTIONS[getRandom().nextInt(4)];
            Structures.whileIntersects(s,w, (ss, t)-> s.translate(vec));

            return callback.apply(w,s);
        });

        builder.add(getStructures());

        return builder.build();
    }

    @Override
    public Stream<Structure> stream() {
        throw new UnsupportedOperationException();
    }
}
