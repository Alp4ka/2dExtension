package structures;

/**
 * This interface lets DAGUtils serialize a class that implements it.
 *
 * @author Gorkovets Roman
 * @version 1.0 ALPHA
 */
public interface Memorizable {
    String serializeHeader();

    String serializeRecord();
}
