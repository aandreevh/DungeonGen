package core.collision.shapes;

import core.collision.ICollideable;
import core.collision.IMovable;
import core.math.unit.INumberUnit;

public interface IShape<T extends INumberUnit> extends ICollideable, IMovable<T> {
}
