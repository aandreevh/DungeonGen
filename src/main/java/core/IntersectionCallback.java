package core;

@FunctionalInterface
public interface IntersectionCallback {

    void onIntersect(Structure structure,AABB box);
}
