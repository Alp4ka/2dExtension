package structures;

import exceptions.DAGConstraintException;
import utils.alogirthms.Color;
import java.util.HashMap;
import java.util.HashSet;

/**
 * Origin defines a coordinate system with it
 * own children - other origins and physical points.
 *
 * @author Gorkovets Roman
 * @version 1.0
 */
public final class Origin implements Movable, Stackable, Memorizable {
    public static final String HEADER_FORMAT = "Origin{address:%d;coordinates:%s}";
    private final HashSet<Movable> children;
    private Coord2D position;
    private BoundBox bounds;

    public Origin() {
        position = null;
        bounds = null;
        children = new HashSet<>();
    }

    public Origin(Coord2D coordinates) {
        position = coordinates;
        bounds = null;
        children = new HashSet<>();
    }

    public Origin(double x, double y) {
        this(new Coord2D(x, y));
    }

    /**
     * Serialize origin as a record.
     *
     * @author Gorkovets Roman
     */
    @Override
    public String serializeRecord() {
        String result = String.format("%d:", System.identityHashCode(this));
        for (var child : getChildren()) {
            if (child instanceof Memorizable) {
                result += String.format("%d;", System.identityHashCode(child));
            }
        }
        return result;
    }

    /**
     * Get all (lower) relatives of current origin.
     *
     * @author Gorkovets Roman
     */
    public HashSet<Movable> getAllChildren() {
        HashSet<Movable> result = new HashSet<>();
        for (var child : children) {
            result.add(child);
            if (child instanceof Origin) {
                result.addAll(((Origin) child).getAllChildren());
            }
        }
        return result;
    }

    /**
     * Update bounds in case something has changed since the last update.
     *
     * @author Gorkovets Roman
     */
    private void updateBounds() {
        if (children == null || children.size() == 0) {
            bounds = null;
            return;
        }
        Coord2D lowerLeftCoord = new Coord2D(Double.MAX_VALUE, Double.MAX_VALUE);
        Coord2D upperRightCoord = new Coord2D(-Double.MAX_VALUE, -Double.MAX_VALUE);
        BoundBox temp;
        for (var child : children) {
            temp = ((Stackable) child).getBounds();
            lowerLeftCoord =
                    new Coord2D(
                            Double.min(lowerLeftCoord.getX(), temp.getLowerLeft().getX()),
                            Double.min(lowerLeftCoord.getY(), temp.getLowerLeft().getY()));
            upperRightCoord =
                    new Coord2D(
                            Double.max(upperRightCoord.getX(), temp.getUpperRight().getX()),
                            Double.max(upperRightCoord.getY(), temp.getUpperRight().getY()));
            if (child instanceof Origin) {
                lowerLeftCoord = lowerLeftCoord.add(child.getPosition());
                upperRightCoord = upperRightCoord.add(child.getPosition());
            }
        }
        bounds = new BoundBox(lowerLeftCoord, upperRightCoord);
    }

    /**
     * Add movable object as a child.
     *
     * @author Gorkovets Roman
     */
    public void addMovable(Movable movable) throws DAGConstraintException, NullPointerException {
        if (movable == null) {
            return;
        }
        children.add(movable);
        if (hasLoop()) {
            children.remove(movable);
            throw new DAGConstraintException("Found a loop while adding new point!");
        }
    }

    public HashSet<Movable> getChildren() {
        return children;
    }

    /**
     * Uses DSF  to detect whether we have a loop in some nodes
     *
     * @author Gorkovets Roman
     */
    public boolean hasLoop() throws NullPointerException {
        HashMap<Movable, Color> all_elements = new HashMap<>();
        return depthSearch(this, all_elements);
    }

    /**
     * Finds loops(DSF).
     *
     * @author Gorkovets Roman
     */
    private boolean depthSearch(Movable node, HashMap<Movable, Color> all_elements) throws NullPointerException {
        if (node == null) {
            throw new NullPointerException("Null point in graph. Unexpected result!");
        }
        all_elements.put(node, Color.GRAY);
        if (node instanceof Origin) {
            for (var child : ((Origin) node).getChildren()) {
                if (!all_elements.containsKey(child)) {
                    all_elements.put(child, Color.WHITE);
                }
                if (all_elements.get(child) == Color.WHITE) {
                    if (depthSearch(child, all_elements)) {
                        return true;
                    }
                } else if (all_elements.get(child) == Color.GRAY) {
                    return true;
                }
            }
        }
        all_elements.put(node, Color.BLACK);
        return false;
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

    /**
     * Get bounds. To avoid errors, every time we get it -
     * - do update.
     *
     * @author Gorkovets Roman
     */
    @Override
    public BoundBox getBounds() {
        updateBounds();
        return bounds;
    }

    /**
     * Serialize origin as header.
     *
     * @author Gorkovets Roman
     */
    @Override
    public String serializeHeader() {
        return String.format(
                HEADER_FORMAT,
                System.identityHashCode(this),
                getPosition().serializeHeader());
    }
}
