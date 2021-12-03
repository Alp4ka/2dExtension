package structures;

import exceptions.DAGConstraintException;
import utils.alogirthms.Color;

import java.util.HashMap;
import java.util.HashSet;

public final class Origin implements Movable, Stackable {
    private Coord2D position;
    private BoundBox bounds;
    private final HashSet<Movable> children;

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

    private void updateBounds() {
        if (children == null || children.size() == 0) {
            bounds = null;
            return;
        }
        double minX = Double.MAX_VALUE, maxX = Double.MIN_VALUE, minY = Double.MAX_VALUE, maxY = Double.MIN_VALUE;
        BoundBox temp;
        for (var child : children) {
            temp = ((Stackable) child).getBounds();
            minX = Double.min(minX, temp.getLowerLeft().getX());
            maxX = Double.max(maxX, temp.getUpperRight().getX());
            minY = Double.min(minY, temp.getLowerLeft().getY());
            maxY = Double.max(maxY, temp.getUpperRight().getY());
            if (child instanceof Origin) {
                minX += child.getPosition().getX();
                maxX += child.getPosition().getX();
                minY += child.getPosition().getY();
                maxY += child.getPosition().getY();
            }
        }
        bounds = new BoundBox(new Coord2D(minX, minY), new Coord2D(maxX, maxY));
    }

    public void addMovable(Movable movable) throws DAGConstraintException {
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

    public boolean hasLoop() {
        HashMap<Movable, Color> all_elements = new HashMap<>();
        return depthSearch(this, all_elements);
    }

    private boolean depthSearch(Movable node, HashMap<Movable, Color> all_elements) {
        if (node == null) {
            throw new NullPointerException("Null point in graph. Can't calculate a loop!");
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
            all_elements.put(node, Color.BLACK);
            return false;
        } else {
            all_elements.put(node, Color.BLACK);
            return false;
        }
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

    @Override
    public BoundBox getBounds() {
        updateBounds();
        return bounds;
    }
}
