package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Coord2DTest {

    @Test
    void getX() {
        Coord2D coord = new Coord2D(7, 2);
        assertEquals((int) coord.getX(), 7);
    }

    @Test
    void getY() {
        Coord2D coord = new Coord2D(7, 2);
        assertEquals((int) coord.getY(), 2);
    }

    @Test
    void testEquals() {
        Coord2D coord1 = new Coord2D(7, 2);
        Coord2D coord2 = new Coord2D(7, 2);
        assertEquals(coord1.equals(coord2), true);
    }

    @Test
    void testHashCode() {
        Coord2D coord1 = new Coord2D(7, 2);
        Coord2D coord2 = new Coord2D(7, 2);
        assertEquals(coord1.hashCode(), coord2.hashCode());
    }

    @Test
    void add() {
        Coord2D coord1 = new Coord2D(7, 2);
        Coord2D coord2 = new Coord2D(7, 2);
        assertEquals(coord1.add(coord2), new Coord2D(14, 4));
    }
}