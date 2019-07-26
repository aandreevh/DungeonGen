package core.struct.gen;

import core.struct.Structure;

import java.util.stream.Stream;

public interface IStreamedStructureGenerator extends IStructureGenerator {

    public Stream<Structure> stream();
}
