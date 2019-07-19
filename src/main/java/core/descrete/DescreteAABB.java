package core.descrete;

import core.collision.shapes.AABB;

public class DescreteAABB extends AABB<Integer> {
    public DescreteAABB(int x, int y, int width, int height) {
        super(x, y, width, height);
    }
}
