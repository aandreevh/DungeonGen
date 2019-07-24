package core.struct;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Structures {


public static void whileIntersects(Structure object, Structure world, BiConsumer<Structure,Structure> callback){
    Optional<Map.Entry<Structure,Structure> > target = world.getIntersectionStructure(object);

    while (target.isPresent()){

        while (target.get().getKey().intersects(target.get().getValue()))
            callback.accept(target.get().getKey(),target.get().getValue());

        target = world.getIntersectionStructure(object);
    }
}
}
