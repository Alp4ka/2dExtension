package structures;

/*
 * Physical points.
 */
public final class Point implements Movable, Stackable {
    private Coord2D position;
    private BoundBox bounds;

    public Point() {
        position = null;
        bounds = null;
    }

    public Point(Coord2D coordinates) {
        position = coordinates;
        bounds = new BoundBox(position, position);
    }

    public Point(double x, double y) {
        this(new Coord2D(x, y));
    }

    @Override
    public Coord2D getPosition() {
        return position;
    }

    @Override
    public void setPosition(Coord2D value) {
        position = new Coord2D(value.getX(), value.getY());
    }

    @Override
    public void setPosition(double x, double y) {
        position = new Coord2D(x, y);
    }

    public BoundBox getBounds() {
        bounds = new BoundBox(position, position);
        return bounds;
    }
}
