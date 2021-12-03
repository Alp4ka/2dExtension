package exceptions;

public class DAGConstraintException extends Exception {
    public DAGConstraintException() {
    }

    public DAGConstraintException(String message) {
        super(message);
    }
}
