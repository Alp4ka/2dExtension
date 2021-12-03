package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointTest {

    @Test
    void getPosition() {
        Point p1 = new Point(1, 5);
        Coord2D position = p1.getPosition();
        assertEquals(position.equals(new Coord2D(5, 1)), false);
    }

    @Test
    void setPosition() {
        Point p1 = new Point(1, 5);
        Point p2 = new Point(2, 7);
        p1.setPosition(2, 7);
        assertEquals(p1.getPosition().equals(p2.getPosition()), true);
    }

    @Test
    void testSetPosition() {
    }

    @Test
    void getBounds() {
    }

    @Test
    void serializeRecord() {
    }

    @Test
    void serializeHeader() {
    }
}