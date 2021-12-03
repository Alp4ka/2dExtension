package structures;

import exceptions.DAGConstraintException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceTest {

    @Test
    void addMovable() throws DAGConstraintException {
        Origin origin1 = new Origin(5, 5);
        Origin origin2 = new Origin(6, 6);
        Space space = new Space(origin1);
        space.addMovable(origin2);
        assertEquals(origin1.getChildren().size(), 1);
    }

    @Test
    void hasLoop() {
        Space s = new Space();
        assertEquals(s.hasLoop(), false);
    }
}