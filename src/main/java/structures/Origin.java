package structures;

import java.util.HashSet;
import java.util.Set;

public final class Origin implements Movable{
    private Coord2D position;
    private BoundBox bounds;
    private HashSet<Movable> children;

    public Origin(){
        position = null;
        bounds= null;
        children = new HashSet<>();
    }

    public Coord2D getPosition(){
        return position;
    }

    public void setPosition(Coord2D value){
        this.position = new Coord2D(value.getX(), value.getY());
    }

    public BoundBox getBounds(){
        return bounds;
    }
}
