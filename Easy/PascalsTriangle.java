import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class PascalsTriangle {
    public List<List<Integer>> bruteForceGenerate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> current = new ArrayList<>();

            for (int column = 0; column <= row; column++) {
                current.add(combination(row, column));
            }

            triangle.add(current);
        }

        return triangle;
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (int row = 0; row < numRows; row++) {
            List<Integer> current = new ArrayList<>();

            for (int column = 0; column <= row; column++) {
                if (column == 0 || column == row) {
                    current.add(1);
                } else {
                    List<Integer> previous = triangle.get(row - 1);
                    current.add(previous.get(column - 1) + previous.get(column));
                }
            }

            triangle.add(current);
        }

        return triangle;
    }

    private int combination(int row, int column) {
        long value = 1;

        for (int count = 1; count <= column; count++) {
            value = value * (row - count + 1) / count;
        }

        return (int) value;
    }

    private static void check(String name, List<List<Integer>> actual, List<List<Integer>> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static List<List<Integer>> rows(Integer[]... rows) {
        List<List<Integer>> triangle = new ArrayList<>();

        for (Integer[] row : rows) {
            triangle.add(Arrays.asList(row));
        }

        return triangle;
    }

    public static void main(String[] args) {
        PascalsTriangle solution = new PascalsTriangle();

        check(
            "brute force builds five rows",
            solution.bruteForceGenerate(5),
            rows(
                new Integer[] {1},
                new Integer[] {1, 1},
                new Integer[] {1, 2, 1},
                new Integer[] {1, 3, 3, 1},
                new Integer[] {1, 4, 6, 4, 1}
            )
        );
        check("brute force builds one row", solution.bruteForceGenerate(1), rows(new Integer[] {1}));

        check(
            "builds five rows",
            solution.generate(5),
            rows(
                new Integer[] {1},
                new Integer[] {1, 1},
                new Integer[] {1, 2, 1},
                new Integer[] {1, 3, 3, 1},
                new Integer[] {1, 4, 6, 4, 1}
            )
        );
        check("builds one row", solution.generate(1), rows(new Integer[] {1}));
    }
}

/*
 * Brute Force:
 * I calculate each row value directly with the binomial coefficient formula,
 * then add that value to the triangle.
 *
 * Time Complexity: O(n^3), where n is numRows, because there are O(n^2)
 * values and each value can take O(n) work to compute.
 * Space Complexity: O(1) extra space beyond the returned triangle.
 *
 * Optimal Interview Solution:
 * I build each row from the row above it. The edges are always one, and each
 * inner value is the sum of the two values above it.
 *
 * Time Complexity: O(n^2), where n is numRows, because each returned value is
 * built once.
 * Space Complexity: O(1) extra space beyond the returned triangle.
 */
