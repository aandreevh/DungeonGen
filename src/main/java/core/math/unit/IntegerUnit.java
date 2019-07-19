package core.math.unit;

public class IntegerUnit implements  INumberUnit {

    private int value;

    public IntegerUnit(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    @Override
    public IntegerUnit add(INumberUnit o) {
        if(o instanceof IntegerUnit) return new IntegerUnit(getValue()+((IntegerUnit) o).getValue());

        throw new NotSupportedUnitOperation();

    }

    @Override
    public IntegerUnit substract(INumberUnit o) {
        if(o instanceof IntegerUnit) return new IntegerUnit(getValue()-((IntegerUnit) o).getValue());

        throw new NotSupportedUnitOperation();
    }

    @Override
    public IntegerUnit multiply(INumberUnit o) {
        if(o instanceof IntegerUnit) return new IntegerUnit(getValue()*((IntegerUnit) o).getValue());

        throw new NotSupportedUnitOperation();
    }

    @Override
    public IntegerUnit divide(INumberUnit o) {
        if(o instanceof IntegerUnit) return new IntegerUnit(getValue()/((IntegerUnit) o).getValue());

        throw new NotSupportedUnitOperation();
    }

    @Override
    public int compareTo(INumberUnit o) {
        if(o instanceof IntegerUnit) return getValue() - ((IntegerUnit) o).getValue();

        throw new NotSupportedUnitOperation();
    }
}
