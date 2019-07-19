package core.math.unit;

public interface INumberUnit extends Comparable<INumberUnit> {

     INumberUnit add(INumberUnit other);
     INumberUnit substract(INumberUnit other);
     INumberUnit multiply(INumberUnit other);
     INumberUnit divide(INumberUnit other);

}
