package structures;

/*
* Mathematical point (virtual).
 */
public final class Coord2D {
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
        return x;
    }

    @Override
    public boolean equals(Object object){
        if (object == this) {
            return true;
        }
        if (object == null || object.getClass() != this.getClass()) {
            return false;
        }

        Coord2D coord = (Coord2D) object;
        if(this.x == coord.x && this.y == coord.y){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
    @Override
    public String toString(){
        return Double.toString(this.x) + ";" + Double.toString(this.y);
    }
}
