package structures;

public final class BoundBox {
    private final Coord2D lowerLeft;
    private final Coord2D upperRight;

    public BoundBox() {
        lowerLeft = null;
        upperRight = null;
    }

    public BoundBox(Coord2D lowerLeft, Coord2D upperRight) {
        this.lowerLeft = lowerLeft;
        this.upperRight = upperRight;
    }

    /**
     * Get lower left bound of square.
     *
     * @author Gorkovets Roman
     */
    public Coord2D getLowerLeft() {
        return lowerLeft;
    }

    /**
     * Get upper right bound of square.
     *
     * @author Gorkovets Roman
     */
    public Coord2D getUpperRight() {
        return upperRight;
    }

    @Override
    public String toString() {
        return String.format("(%s) : (%s)", getLowerLeft(), getUpperRight());
    }
}
