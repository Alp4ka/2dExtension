package utils;

import exceptions.DAGConstraintException;
import org.junit.jupiter.api.Test;
import structures.Origin;
import structures.Point;
import structures.Space;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

class DAGUtilsTest {

    @Test
    void exportAsString() throws DAGConstraintException {
        Space space = new Space();
        Origin o1 = new Origin(2, 5);
        Point p1 = new Point(1.67, 1);
        space.addMovable(o1);
        space.addMovable(p1);
        o1.addMovable(p1);
        var result = DAGUtils.exportAsString(space);
        assertNotNull(result);
    }

    @Test
    void importFromStringWrongFormat() throws ParseException {
        var result = DAGUtils.importFromString("Ya nepravilniy");
        assertNull(result);
    }

    @Test
    void importFromString() throws ParseException, DAGConstraintException {
        Space space = new Space();
        Origin o1 = new Origin(2, 5);
        Point p1 = new Point(1.67, 1);
        space.addMovable(o1);
        space.addMovable(p1);
        o1.addMovable(p1);
        var line = DAGUtils.exportAsString(space);
        line = line.replace(',', '|').replace('.', '|');
        var result = DAGUtils.importFromString(line);
        assertNotEquals(result, null);
    }
}