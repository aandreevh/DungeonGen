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

        getAdapters().putIfAbsent(A,new HashMap<>()).put(B,adapter);
        getAdapters().putIfAbsent(B,new HashMap<>()).put(A,adapter);

    }

    public ICollisionAdapter getAdapter(Class<? extends ICollideable> A,Class<? extends ICollideable> B){
        return getAdapters().get(A).get(B);
    }

}
