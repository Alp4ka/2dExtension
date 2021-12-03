package structures;


/**
 * Defines a structures which are able to move in space.
 *
 * @author Gorkovets Roman
 * @version 1.0
 */
public interface Movable {
    void setPosition(Coord2D value);

    void setPosition(double x, double y);

    Coord2D getPosition();
}
