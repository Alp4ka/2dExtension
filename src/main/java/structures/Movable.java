package structures;

public interface Movable {
    void setPosition(Coord2D value);

    void setPosition(double x, double y);

    Coord2D getPosition();
}
