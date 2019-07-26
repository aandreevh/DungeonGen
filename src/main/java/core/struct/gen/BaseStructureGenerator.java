package core.struct.gen;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Random;
import java.util.stream.Stream;

public class BaseStructureGenerator implements IStreamedStructureGenerator {

    private Random rand;
    private int minWidth,maxWidth,minHeight,maxHeight;

    public BaseStructureGenerator(int minWidth, int minHeight, int maxWidth, int maxHeight){
        this(new Random(),minWidth,maxWidth,minHeight,maxHeight);
    }

    private void setRandom(Random rand){
        this.rand = rand;
    }

    @Override
    public Random getRandom() {
        return this.rand;
    }

    public BaseStructureGenerator(Random rand, int minWidth, int minHeight, int maxWidth, int maxHeight){
        setRandom(rand);
        this.minWidth = minWidth;
        this.maxWidth = maxWidth;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    @Override
    public Structure generate() {
      return new Structure(new AABB(0,0,getRandom().nextInt(maxWidth-minWidth)+minWidth,
                getRandom().nextInt(maxHeight-minHeight)+minHeight));

    }


    @Override
    public Stream<Structure> stream() {
        return Stream.generate(this::generate);
    }
}
