package core.struct.gen;

import core.geom.Point;
import core.struct.Structure;
import core.struct.utils.StructureBuilder;
import core.struct.utils.Structures;

import java.util.Collection;
import java.util.Random;
import java.util.function.BiConsumer;

public class CompositeStructureGenerator implements IStructureGenerator {


    private Random rand;
    private Collection<Structure> structures;
    private BiConsumer<Structure,Structure> callback;
    private StructureBuilder builder;


    protected Collection<Structure> getStructures(){
        return this.structures;
    }


    public CompositeStructureGenerator(Random rand, Collection<Structure> structs){
        this(rand,structs,(w,s)->w.attach(s));
    }


    public CompositeStructureGenerator(Random rand, Collection<Structure> structs,
                                       BiConsumer<Structure, Structure> callback){
        setRandom(rand);
        this.structures = structs;
        this.callback = callback;
    }

    private void setRandom(Random rand){
        this.rand = rand;
    }

    @Override
    public Random getRandom() {
        return this.rand;
    }

    @Override
    public Structure generate() {
        if(builder !=null) throw new UnsupportedOperationException();

        builder = new StructureBuilder((w,s)->{
            Point p = Point.ZERO;

            if(w.getSubStructures().size()>0)
               p= w.getSubStructures().get(getRandom().nextInt(w.getSubStructures().size())).getAABB().getMaxPoint();

            s.translate(p);

            final Point vec = Point.DIRECTIONS[getRandom().nextInt(4)];
            Structures.whileIntersects(s,w, (ss, t)-> s.translate(vec));

            callback.accept(w,s);
        });

        builder.add(getStructures());

        return builder.build();
    }

}
