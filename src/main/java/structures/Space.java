package structures;

/**
 * Space class implements the DAG structure of Movable objects.
 *
 * @author Gorkovets Roman
 * @version 1.0
 */
public final class Space {
    private Origin benchmarkPoint;

    public Space() {
        benchmarkPoint = null;
    }

    public Space(Origin benchmarkPoint) {
        this();
        this.benchmarkPoint = benchmarkPoint;
    }

    public Space(double x, double y) {
        this();
        this.benchmarkPoint = new Origin(x, y);
    }

    public void setBenchmarkPoint(Origin benchmarkPoint) {
        this.benchmarkPoint = benchmarkPoint;
    }

    public boolean hasLoop() {
        if (benchmarkPoint != null) {
            return benchmarkPoint.hasLoop();
        }
        return false;
    }

}
