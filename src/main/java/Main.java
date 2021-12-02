import exceptions.DAGConstraintException;
import structures.Coord2D;
import structures.Point;


class Main {
    public static void main(String[] args) throws DAGConstraintException {
        Coord2D x = new Coord2D(1, 2);
        Point p = new Point(3, 3);
        System.out.println(p.getBounds().getLowerLeft());
        System.out.println(p.getBounds().getUpperRight());
        p.setPosition(x);
        System.out.println(p.getBounds().getLowerLeft());
        System.out.println(p.getBounds().getUpperRight());
    }
}
