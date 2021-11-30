package structures;
import structures.Coord2D;

/*
* Physical points.
 */
public final class Point implements Movable{
    private Coord2D position;
    private BoundBox bounds;

    public Coord2D getPosition(){
        return this.position;
    }

    public void setPosition(Coord2D value){
        this.position = new Coord2D(value.getX(), value.getY());
    }

    public BoundBox getBounds(){
        return bounds;
    }
}
