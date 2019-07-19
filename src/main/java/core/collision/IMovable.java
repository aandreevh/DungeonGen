package core.collision;

import core.math.unit.INumberUnit;

public interface IMovable<T extends INumberUnit> {


    IMovable offset(T x,T y);
}
