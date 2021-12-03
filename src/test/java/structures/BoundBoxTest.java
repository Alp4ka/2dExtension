package structures;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BoundBoxTest {


    @Test
    void testToString() {
        Coord2D coord1 = new Coord2D(1, 1);
        Coord2D coord2 = new Coord2D(2, 2);
        BoundBox bbox = new BoundBox(coord1, coord2);
        var result = String.format("(%s) : (%s)", coord1, coord2);
        assertEquals(bbox.toString(), result);
    }
}