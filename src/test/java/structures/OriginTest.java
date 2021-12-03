package structures;

import exceptions.DAGConstraintException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

class OriginTest {

    @Test
    void serializeRecord() {
    }

    @Test
    void getAllChildren() {
    }

    @Test
    void addMovableLoop() {
        Origin origin = new Origin(1, 1);
        assertThrowsExactly(DAGConstraintException.class, () -> origin.addMovable(origin));
    }

    @Test
    void addMovable() throws DAGConstraintException {
        Origin origin = new Origin(1, 1);
        origin.addMovable(new Point(1, 2));
        assertEquals(origin.getAllChildren().size(), 1);
    }

    @Test
    void getChildren() throws DAGConstraintException {
        Origin origin = new Origin(1, 1);
        origin.addMovable(new Point(1, 2));
        origin.addMovable(new Point(1, 2));
        origin.addMovable(new Point(2, 5));
        assertEquals(origin.getChildren().size(), 3);
    }

    @Test
    void setPosition() {
        Origin origin = new Origin(1, 1);
        origin.setPosition(5, 5);
        Coord2D c2 = new Coord2D(5, 5);
        assertEquals(origin.getPosition().equals(c2), true);
    }
}