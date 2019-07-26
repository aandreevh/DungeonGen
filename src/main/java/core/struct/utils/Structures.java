package core.struct;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Structures {


    /** Slow **/
public static void whileIntersects(Structure a, Structure b, BiConsumer<Structure,Structure> callback){
    Optional<Map.Entry<Structure,Structure> > target = b.getIntersectionStructure(a);

    while (target.isPresent()){

        while (target.get().getKey().intersects(target.get().getValue()))
            callback.accept(target.get().getKey(),target.get().getValue());

        target = b.getIntersectionStructure(a);
    }
}
}
