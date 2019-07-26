package core.struct.gen;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Random;

public class BasicStructureGenerator extends IStreamedStructureGenerator {

    private int minWidth,maxWidth,minHeight,maxHeight;

    public BasicStructureGenerator(int minWidth, int minHeight, int maxWidth, int maxHeight){
        this(new Random(),minWidth,maxWidth,minHeight,maxHeight);
    }

    public BasicStructureGenerator(Random rand, int minWidth, int minHeight, int maxWidth, int maxHeight){
        super(rand);
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
}
