package core.struct.utils;

import core.struct.Structure;

import java.util.Collection;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class StructureBuilder {


    private Structure world = new Structure();
    private BiConsumer<Structure,Structure> callback;

    public StructureBuilder( ){this.callback = (w,s)->w.attach(s);}

    public StructureBuilder(BiConsumer<Structure,Structure> callback){this.callback = callback;}


    public Structure build(Consumer<Structure> onBuildCallback){
        onBuildCallback.accept(getWorld());
        return getWorld();
    }

    public Structure build(){
        return getWorld();
    }

    public StructureBuilder add(Collection<Structure> objects){
        objects.stream().forEach(o->callback.accept(getWorld(),o));
        return this;
    }



    protected Structure getWorld(){
        return world;
    }
}
