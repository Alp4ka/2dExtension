import exceptions.DAGConstraintException;
import structures.Origin;
import structures.Point;


class Main {
    public static void main(String[] args) throws DAGConstraintException {
        Origin o1 = new Origin(1, 1);
        Origin o2 = new Origin(3, 1);
        Point p1 = new Point(3, -1);
        Point p2 = new Point(-1, 6);
        o1.addMovable(o2);
        o2.addMovable(p1);
        o2.addMovable(p2);
        System.out.println(p1.getBounds());
        System.out.println(p2.getBounds());
        System.out.println(o2.getBounds());
        System.out.println(o1.getBounds());
    }
}
