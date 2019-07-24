package core.struct.gen;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Random;

public class BasicRoomGenerator extends StructureGenerator {

    private int minWidth,maxWidth,minHeight,maxHeight;

    public BasicRoomGenerator(int minWidth, int minHeight, int maxWidth, int maxHeight){
        this(new Random(),minWidth,maxWidth,minHeight,maxHeight);
    }

    public BasicRoomGenerator(Random rand, int minWidth, int minHeight, int maxWidth, int maxHeight){
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
