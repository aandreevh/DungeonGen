package core.struct;

import core.geom.AABB;
import core.geom.Point;

import java.util.*;

public class Structure implements Iterable<Structure> {

    private Structure parent = null;

    private AABB globalAABB;
    private ArrayList<Structure> subStructures = new ArrayList<>();
    private boolean isBase;

    public Structure() {
        setAABB(AABB.ZERO);
        setIsBase(false);
    }

    public Structure(AABB aabb){
        setAABB(aabb);
        setIsBase(true);
    }

    public boolean isBase(){
        return isBase;
    }

    public AABB getAABB() {
        return this.globalAABB;
    }


    public ArrayList<Structure> getSubStructures() {
        if(isBase()) throw new UnsupportedOperationException();

        return subStructures;
    }

    public Optional<Structure> getParent() {
        return Optional.ofNullable(parent);
    }

    public void setBaseAABB(AABB aabb){
        if(!isBase()) throw new UnsupportedOperationException("cannot set base AABB to not base structure");
        this.setAABB(aabb);
        recalculateParentAABB();
    }

    public void translate(Point p){
        translate(p.getX(),p.getY());
    }

    public void translate(int dx, int dy) {
        translateUnsafe(dx,dy);
        recalculateParentAABB();
    }

    public Optional<Map.Entry<Structure,Structure>> getIntersectionStructure(Structure other) {
        if(!getAABB().intersects(other.getAABB())) return Optional.empty();

        if(isBase()){
            if(other.isBase()){
                return Optional.of(Map.entry(this,other));
            }else{
                return other.getSubStructures().stream().map(s -> s.getIntersectionStructure(this))
                        .filter(Optional::isPresent)
                        .findAny().orElse( Optional.empty());
            }
        }else{
            return getSubStructures().stream().map(s -> s.getIntersectionStructure(other))
                    .filter(Optional::isPresent)
                    .findAny().orElse( Optional.empty());
        }
    }


    public boolean intersects(Structure other) {
        if(!getAABB().intersects(other.getAABB())) return false;

        if(isBase()){
            if(other.isBase()){
                return true;
            }else{
                return other.getSubStructures().stream().anyMatch(s -> s.intersects(this));
            }
        }else{

            return getSubStructures().stream().anyMatch(s -> s.intersects(other));

        }
    }

    public boolean intersects(AABB aabb) {
        if(!getAABB().intersects(aabb)) return false;
        if(isBase()) return true;
        return getSubStructures().stream().anyMatch(s -> s.intersects(aabb));

    }


    public void attach(Structure... ss){
        if(isBase()) throw  new UnsupportedOperationException();

        for(Structure s : ss){
            if (this.equals(s)) continue;
            if(!s.setParent(this)) throw new  IllegalArgumentException("Structure has parent");
            this.getSubStructures().add(s);
            setAABB(getAABB().append(s.getAABB()));
        }

        recalculateParentAABB();
    }

    public void detach(){
        if(!getParent().isPresent()) throw new NullPointerException("Structure does not have parent");

        if(!getParent().get().getSubStructures().remove(this))
           throw  new RuntimeException("Invalid structure hierarchy");

        getParent().get().recalculateAABB();
        this.parent = null;
    }


    protected void setAABB(AABB aabb){
        this.globalAABB = aabb;
    }

    protected void setIsBase(boolean flag){
        isBase = flag;
    }

    private void translateUnsafe(int dx, int dy) {
        if(isBase()){
            setAABB(getAABB().translate(dx,dy));
        }else {
            getSubStructures().forEach(s -> s.translateUnsafe(dx, dy));
            setAABB(getAABB().translate(dx, dy));
        }
    }

    private boolean setParent(Structure s){
        if(getParent().isPresent()) return false;
        this.parent = s;

        return true;
    }
    private void recalculateParentAABB(){
        getParent().ifPresent(Structure::recalculateAABB);
    }

    private void recalculateAABB(){
        if(isBase()) throw new UnsupportedOperationException();
        setAABB(AABB.ZERO);
        getSubStructures().forEach(s -> setAABB(getAABB().append(s.getAABB())));
        recalculateParentAABB();
    }

    @Override
    public String toString() {
        return "Structure{" +
                "AABB=" + globalAABB +
                (isBase()? "" :  ", subStructures=" + subStructures) +
                '}';
    }

    @Override
    public Iterator<Structure> iterator() {
        return getSubStructures().iterator();
    }
}
