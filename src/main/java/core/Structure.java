package core;

import java.util.*;

public class Structure {

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


    public void translate(int dx, int dy) {
        translateUnsafe(dx,dy);
        getParent().ifPresent(Structure::recalculateAABB);
    }


    public boolean intersects(Structure other) {
        if(!getAABB().intersects(other.getAABB())) return false;

        if(isBase()){
            if(other.isBase()){
                return true;
            }else{
                return other.getSubStructures().stream().anyMatch(s -> s.intersects(other));
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
            if(!s.setParent(this)) continue;
            this.getSubStructures().add(s);
            setAABB(getAABB().append(s.getAABB()));
        }

        recalculateParentAABB();
    }

    public boolean deattach(){
        if(!getParent().isPresent()) return false;

        if(!getParent().get().getSubStructures().remove(this))
           throw  new RuntimeException("Invalid structure hierarchy");

        getParent().get().recalculateAABB();

       return removeParent();
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


    private boolean removeParent(){
        if(!getParent().isPresent()) return false;
        this.parent = null;

        return true;
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
}
