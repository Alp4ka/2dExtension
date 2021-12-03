package structures;

import exceptions.DAGConstraintException;

/**
 * Space class implements the DAG structure of Movable objects.
 *
 * @author Gorkovets Roman
 * @version 1.0
 */
public final class Space implements Memorizable {
    public static final String HEADER_FORMAT = "Space{benchmark:%d}";
    private final Origin benchmarkOrigin;

    public Space() {
        benchmarkOrigin = new Origin(0, 0);
    }

    public Space(double x, double y) {
        benchmarkOrigin = new Origin(x, y);
    }

    public Space(Coord2D coord) {
        benchmarkOrigin = new Origin(coord);
    }

    public Space(Origin origin) {
        benchmarkOrigin = origin;
    }

    public Origin getBenchmarkOrigin() {
        return benchmarkOrigin;
    }

    public void addMovable(Movable movable) throws DAGConstraintException {
        if (benchmarkOrigin != null) {
            benchmarkOrigin.addMovable(movable);
        }
    }

    public boolean hasLoop() {
        if (benchmarkOrigin != null) {
            return benchmarkOrigin.hasLoop();
        }
        return false;
    }

    @Override
    public String serializeRecord() {
        return null;
    }

    @Override
    public String serializeHeader() {
        return String.format(HEADER_FORMAT, System.identityHashCode(getBenchmarkOrigin()));
    }
}
