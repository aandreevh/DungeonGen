package core.collision;

import java.util.HashMap;
public class CollisionManager {
    private static CollisionManager instance = new CollisionManager();

    public static CollisionManager getInstance() {
        return instance;
    }


    private HashMap<Class<? extends ICollideable>,HashMap<Class<? extends ICollideable>, ICollisionAdapter> > adapters
            = new HashMap<>();

    public HashMap<Class<? extends ICollideable>, HashMap<Class<? extends ICollideable>, ICollisionAdapter>> getAdapters() {
        return adapters;
    }

    private CollisionManager() {

    }

    public void registerCollisionAdapter(Class<? extends ICollideable> A,Class<? extends ICollideable> B,
                                            ICollisionAdapter adapter){

        var amap = getAdapters().putIfAbsent(A,new HashMap<>()).putIfAbsent(B,new HashMap<>());


    }

    public ICollisionAdapter getAdapter(){
        return
    }

}
