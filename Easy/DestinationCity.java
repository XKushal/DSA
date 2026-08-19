import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class DestinationCity {
    public String bruteForceDestCity(List<List<String>> paths) {
        for (List<String> path : paths) {
            String candidate = path.get(1);
            boolean hasOutgoingPath = false;

            for (List<String> otherPath : paths) {
                if (candidate.equals(otherPath.get(0))) {
                    hasOutgoingPath = true;
                    break;
                }
            }

            if (!hasOutgoingPath) {
                return candidate;
            }
        }

        return "";
    }

    public String destCity(List<List<String>> paths) {
        Set<String> sourceCities = new HashSet<>();

        for (List<String> path : paths) {
            sourceCities.add(path.get(0));
        }

        for (List<String> path : paths) {
            String destination = path.get(1);
            if (!sourceCities.contains(destination)) {
                return destination;
            }
        }

        return "";
    }

    private static void check(String name, String actual, String expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static List<String> path(String from, String to) {
        return Arrays.asList(from, to);
    }

    @SafeVarargs
    private static List<List<String>> paths(List<String>... values) {
        return Arrays.asList(values);
    }

    public static void main(String[] args) {
        DestinationCity solution = new DestinationCity();

        check("brute force three paths",
            solution.bruteForceDestCity(paths(path("London", "New York"), path("New York", "Lima"),
                path("Lima", "Sao Paulo"))),
            "Sao Paulo");
        check("brute force single path", solution.bruteForceDestCity(paths(path("B", "C"))), "C");

        check("three paths",
            solution.destCity(paths(path("London", "New York"), path("New York", "Lima"),
                path("Lima", "Sao Paulo"))),
            "Sao Paulo");
        check("single path", solution.destCity(paths(path("B", "C"))), "C");
        check("branching names", solution.destCity(paths(path("A", "Z"), path("Z", "Y"), path("Y", "X"))), "X");
        check("unordered paths", solution.destCity(paths(path("C", "D"), path("A", "B"), path("B", "C"))), "D");
    }
}

/*
 * Brute Force:
 * I try every destination city and scan all paths to see whether that city ever
 * appears as a source city. The first destination without an outgoing path is
 * the final city.
 *
 * Time Complexity: O(n^2), because each destination may scan every path.
 * Space Complexity: O(1), because only a few variables are used.
 *
 * Optimal Interview Solution:
 * I store every source city in a set, then return the destination city that is
 * absent from that set.
 *
 * Time Complexity: O(n), because the paths are scanned twice.
 * Space Complexity: O(n), because the source city set can store every path's
 * starting city.
 */
