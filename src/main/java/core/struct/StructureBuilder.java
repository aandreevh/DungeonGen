package core.struct;

import java.util.Collection;
import java.util.function.BiFunction;
import java.util.function.Consumer;

public class StructureBuilder {


    private Structure world = new Structure();
    private BiFunction<Structure,Structure,Boolean> callback;

    public StructureBuilder( ){this.callback = (w,s)->true;}

    public StructureBuilder(BiFunction<Structure,Structure,Boolean> callback){this.callback = callback;}


    public Structure build(Consumer<Structure> onBuildCallback){
        onBuildCallback.accept(getWorld());
        return getWorld();
    }

    public Structure build(){
        return getWorld();
    }

    public StructureBuilder add(Collection<Structure> objects){
        objects.stream().filter(s -> callback.apply(getWorld(),s)).forEach(getWorld()::attach);
        return this;
    }



    protected Structure getWorld(){
        return world;
    }
}
