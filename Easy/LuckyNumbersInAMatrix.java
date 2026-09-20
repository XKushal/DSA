import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class LuckyNumbersInAMatrix {
    public List<Integer> bruteForceLuckyNumbers(int[][] matrix) {
        List<Integer> result = new ArrayList<>();

        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (isMinimumInRow(matrix, row, col) && isMaximumInColumn(matrix, row, col)) {
                    result.add(matrix[row][col]);
                }
            }
        }

        return result;
    }

    public List<Integer> luckyNumbers(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int[] rowMinimums = new int[rows];
        int[] columnMaximums = new int[cols];

        Arrays.fill(rowMinimums, Integer.MAX_VALUE);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rowMinimums[row] = Math.min(rowMinimums[row], matrix[row][col]);
                columnMaximums[col] = Math.max(columnMaximums[col], matrix[row][col]);
            }
        }

        List<Integer> result = new ArrayList<>();

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == rowMinimums[row] && matrix[row][col] == columnMaximums[col]) {
                    result.add(matrix[row][col]);
                }
            }
        }

        return result;
    }

    private boolean isMinimumInRow(int[][] matrix, int targetRow, int targetCol) {
        for (int col = 0; col < matrix[targetRow].length; col++) {
            if (matrix[targetRow][col] < matrix[targetRow][targetCol]) {
                return false;
            }
        }

        return true;
    }

    private boolean isMaximumInColumn(int[][] matrix, int targetRow, int targetCol) {
        for (int row = 0; row < matrix.length; row++) {
            if (matrix[row][targetCol] > matrix[targetRow][targetCol]) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, List<Integer> actual, List<Integer> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        LuckyNumbersInAMatrix solution = new LuckyNumbersInAMatrix();

        check("brute force sample one", solution.bruteForceLuckyNumbers(new int[][] {
                {3, 7, 8},
                {9, 11, 13},
                {15, 16, 17}
        }), Arrays.asList(15));
        check("brute force sample two", solution.bruteForceLuckyNumbers(new int[][] {
                {1, 10, 4, 2},
                {9, 3, 8, 7},
                {15, 16, 17, 12}
        }), Arrays.asList(12));

        check("sample one", solution.luckyNumbers(new int[][] {
                {3, 7, 8},
                {9, 11, 13},
                {15, 16, 17}
        }), Arrays.asList(15));
        check("sample two", solution.luckyNumbers(new int[][] {
                {1, 10, 4, 2},
                {9, 3, 8, 7},
                {15, 16, 17, 12}
        }), Arrays.asList(12));
        check("single row", solution.luckyNumbers(new int[][] {{7, 8, 9}}), Arrays.asList(7));
        check("single column", solution.luckyNumbers(new int[][] {{5}, {1}, {3}}), Arrays.asList(5));
        check("two by two", solution.luckyNumbers(new int[][] {
                {7, 8},
                {1, 2}
        }), Arrays.asList(7));
    }
}

/*
 * Brute Force:
 * I test every cell and scan its row and column to confirm it is the row
 * minimum and the column maximum.
 *
 * Time Complexity: O(m * n * (m + n)), where m is the number of rows and n is
 * the number of columns.
 * Space Complexity: O(1), excluding the output list.
 *
 * Optimal Interview Solution:
 * I precompute every row minimum and column maximum, then collect the cells
 * that match both values.
 *
 * Time Complexity: O(m * n), because the matrix is scanned twice.
 * Space Complexity: O(m + n), because row minimums and column maximums are
 * stored.
 */
