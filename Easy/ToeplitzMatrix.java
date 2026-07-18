class ToeplitzMatrix {
    public boolean bruteForceIsToeplitzMatrix(int[][] matrix) {
        for (int row = 0; row < matrix.length; row++) {
            for (int column = 0; column < matrix[row].length; column++) {
                int nextRow = row + 1;
                int nextColumn = column + 1;

                while (nextRow < matrix.length && nextColumn < matrix[nextRow].length) {
                    if (matrix[nextRow][nextColumn] != matrix[row][column]) {
                        return false;
                    }

                    nextRow++;
                    nextColumn++;
                }
            }
        }

        return true;
    }

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int row = 1; row < matrix.length; row++) {
            for (int column = 1; column < matrix[row].length; column++) {
                if (matrix[row][column] != matrix[row - 1][column - 1]) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ToeplitzMatrix solution = new ToeplitzMatrix();

        check("brute force accepts matching diagonals",
                solution.bruteForceIsToeplitzMatrix(new int[][] {
                        {1, 2, 3, 4},
                        {5, 1, 2, 3},
                        {9, 5, 1, 2}
                }), true);
        check("brute force rejects changed diagonal",
                solution.bruteForceIsToeplitzMatrix(new int[][] {
                        {1, 2},
                        {2, 2}
                }), false);

        check("accepts matching diagonals",
                solution.isToeplitzMatrix(new int[][] {
                        {1, 2, 3, 4},
                        {5, 1, 2, 3},
                        {9, 5, 1, 2}
                }), true);
        check("rejects changed diagonal",
                solution.isToeplitzMatrix(new int[][] {
                        {1, 2},
                        {2, 2}
                }), false);
        check("handles single row",
                solution.isToeplitzMatrix(new int[][] {
                        {1, 2, 3}
                }), true);
        check("handles single column",
                solution.isToeplitzMatrix(new int[][] {
                        {1},
                        {2},
                        {3}
                }), true);
    }
}

/*
 * Brute Force:
 * I start from every cell and walk down its diagonal, checking that each later
 * value matches the starting value.
 *
 * Time Complexity: O(m * n * min(m, n)), where m is the row count and n is the
 * column count, because each cell can scan the rest of its diagonal.
 * Space Complexity: O(1), because only loop counters are stored.
 *
 * Optimal Interview Solution:
 * I scan every cell except the first row and first column, and compare it with
 * the previous cell on the same diagonal.
 *
 * Time Complexity: O(m * n), where m is the row count and n is the column count.
 * Space Complexity: O(1), because no extra data structures are needed.
 */
