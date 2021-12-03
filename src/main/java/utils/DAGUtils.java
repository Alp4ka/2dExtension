package utils;

import structures.*;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;

public class DAGUtils {
    private static final String SPLIT_SECTION_SEPARATOR = "/";
    private static final String END_SECTION_SEPARATOR = "#";

    private static String serializeHeader(Memorizable memorizable) {
        return memorizable.serializeHeader();
    }

    private static String serializeRecord(Memorizable memorizable) {
        return memorizable.serializeRecord();
    }

    private static Memorizable parseHeader(String line, HashMap<Integer, Memorizable> mapping) throws ParseException {
        Memorizable result;
        String addressAttributeRepresentation = "address:";
        String coordinatesAttributeRepresentation = "coordinates:";
        if (line.contains("Space")) {
            return null;
        }
        int addressIndex = line.indexOf(addressAttributeRepresentation) + addressAttributeRepresentation.length();
        int separatorIndex = line.indexOf(";");
        int address = Integer.parseInt(line.substring(addressIndex, separatorIndex));
        int coordIndex = line.indexOf(coordinatesAttributeRepresentation) + coordinatesAttributeRepresentation.length();
        int bracketIndex = line.lastIndexOf("}");
        Coord2D coordinates = parseCoordinates(line.substring(coordIndex, bracketIndex));
        if (line.contains("Origin")) {
            result = new Origin(coordinates);
            mapping.put(address, result);
            return result;
        } else if (line.contains("Point")) {
            result = new Point(coordinates);
            mapping.put(address, result);
            return result;
        } else {
            return null;
        }
    }

    private static int getSpaceBenchmark(String line) {
        String benchmarkAttributeRepresentation = "benchmark:";
        int benchmarkIndex = line.indexOf(benchmarkAttributeRepresentation) + benchmarkAttributeRepresentation.length();
        int bracketIndex = line.lastIndexOf("}");
        return Integer.parseInt(line.substring(benchmarkIndex, bracketIndex));
    }

    private static Coord2D parseCoordinates(String line) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
        String xAttributeRepresentation = "x:";
        String yAttributeRepresentation = "y:";
        int xIndex = line.indexOf(xAttributeRepresentation) + xAttributeRepresentation.length();
        int commaIndex = line.indexOf(";");
        int yIndex = line.indexOf(yAttributeRepresentation) + yAttributeRepresentation.length();
        int bracketIndex = line.lastIndexOf("}");
        Number number = format.parse(line.substring(xIndex, commaIndex));
        double x = number.doubleValue();
        number = format.parse(line.substring(yIndex, bracketIndex));
        double y = number.doubleValue();
        return new Coord2D(x, y);
    }

    public static String exportAsString(Space space) {
        String result = "";
        result += serializeHeader(space) + SPLIT_SECTION_SEPARATOR;
        result += serializeHeader(space.getBenchmarkOrigin()) + SPLIT_SECTION_SEPARATOR;
        var allChildren = space.getBenchmarkOrigin().getAllChildren();
        for (var child : allChildren) {
            if (child instanceof Memorizable) {
                result += serializeHeader((Memorizable) child) + SPLIT_SECTION_SEPARATOR;
            }
        }
        result += END_SECTION_SEPARATOR;

        allChildren.add(space.getBenchmarkOrigin());
        for (var child : allChildren) {
            if (child instanceof Memorizable) {
                String temp = serializeRecord((Memorizable) child);
                if (temp != null) {
                    result += temp + SPLIT_SECTION_SEPARATOR;
                }

            }
        }
        return result;
    }

    public static Space importFromString(String line) throws ParseException {
        try {
            Space result;
            var parts = line.split(END_SECTION_SEPARATOR);
            if (parts.length != 2) {
                return null;
            }
            var mapping = new HashMap<Integer, Memorizable>();
            int spaceBenchmarkAddress = -1;
            var headers = parts[0].split(SPLIT_SECTION_SEPARATOR);
            for (var header : headers) {
                Memorizable object = parseHeader(header, mapping);
                if (object == null) {
                    spaceBenchmarkAddress = getSpaceBenchmark(header);
                }
            }
            result = new Space((Origin) mapping.get(spaceBenchmarkAddress));

            var records = parts[1].split(SPLIT_SECTION_SEPARATOR);
            for (var record : records) {
                var record_parts = record.split(":");
                if (record_parts.length != 2) {
                    return null;
                }
                int parent_address = Integer.parseInt(record_parts[0].replace(';', ' ').trim());
                var children = record_parts[1].split(";");
                for (var child : children) {
                    if (mapping.get(parent_address) instanceof Origin) {
                        var child_instance = mapping.get(Integer.parseInt(child));
                        if (child_instance instanceof Movable) {
                            ((Origin) mapping.get(parent_address)).addMovable((Movable) child_instance);
                        }
                    }
                }
            }
            return result;
        } catch (Exception ex) {
            throw new ParseException("Error while parsing data of space!", -1);
        }
    }
}
