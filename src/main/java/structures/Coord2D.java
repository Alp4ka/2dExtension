package structures;

/**
 * Mathematical(virtual) point class.
 *
 * @author Gorkovets Roman
 * @version 1.0
 */
public final class Coord2D implements Memorizable {
    public static final String HEADER_FORMAT = "Coordinates{x:%f;y:%f}";
    private final double x;
    private final double y;

    public Coord2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    @Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Coord2D coord = (Coord2D) object;
        return this.x == coord.x && this.y == coord.y;
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }

    /**
     * Summarize original coord with the second one and return a result.
     *
     * @author Gorkovets Roman
     */
    public Coord2D add(Coord2D secondCoord) {
        return new Coord2D(x + secondCoord.getX(), y + secondCoord.getY());
    }

    @Override
    public String toString() {
        return this.x + ";" + this.y;
    }

    /**
     * Serialize coord as string.
     *
     * @author Gorkovets Roman
     */
    @Override
    public String serializeHeader() {
        return String.format(HEADER_FORMAT, getX(), getY());
    }

    /**
     * Serialize coord as record.
     *
     * @author Gorkovets Roman
     */
    @Override
    public String serializeRecord() {
        return null;
    }
}
