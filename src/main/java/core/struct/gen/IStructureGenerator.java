package core.struct.gen;

import core.geom.AABB;
import core.struct.Structure;

import java.util.Random;
import java.util.stream.Stream;

public interface IStructureGenerator {

     Random getRandom();

     Structure generate();

}
