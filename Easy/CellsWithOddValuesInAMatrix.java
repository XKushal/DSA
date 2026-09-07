class CellsWithOddValuesInAMatrix {
    public int bruteForceOddCells(int m, int n, int[][] indices) {
        int[][] matrix = new int[m][n];

        for (int[] index : indices) {
            int row = index[0];
            int col = index[1];

            for (int c = 0; c < n; c++) {
                matrix[row][c]++;
            }

            for (int r = 0; r < m; r++) {
                matrix[r][col]++;
            }
        }

        int oddCells = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (matrix[r][c] % 2 == 1) {
                    oddCells++;
                }
            }
        }

        return oddCells;
    }

    public int oddCells(int m, int n, int[][] indices) {
        boolean[] oddRows = new boolean[m];
        boolean[] oddCols = new boolean[n];

        for (int[] index : indices) {
            oddRows[index[0]] = !oddRows[index[0]];
            oddCols[index[1]] = !oddCols[index[1]];
        }

        int oddRowCount = 0;
        for (boolean isOdd : oddRows) {
            if (isOdd) {
                oddRowCount++;
            }
        }

        int oddColCount = 0;
        for (boolean isOdd : oddCols) {
            if (isOdd) {
                oddColCount++;
            }
        }

        return oddRowCount * (n - oddColCount) + (m - oddRowCount) * oddColCount;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CellsWithOddValuesInAMatrix solution = new CellsWithOddValuesInAMatrix();

        check("brute force sample", solution.bruteForceOddCells(
                2, 3, new int[][] {{0, 1}, {1, 1}}), 6);
        check("brute force overlapping updates", solution.bruteForceOddCells(
                2, 2, new int[][] {{1, 1}, {0, 0}}), 0);

        check("sample", solution.oddCells(
                2, 3, new int[][] {{0, 1}, {1, 1}}), 6);
        check("overlapping updates", solution.oddCells(
                2, 2, new int[][] {{1, 1}, {0, 0}}), 0);
        check("single update", solution.oddCells(
                3, 4, new int[][] {{1, 2}}), 5);
        check("repeated row and column", solution.oddCells(
                3, 3, new int[][] {{0, 1}, {0, 1}, {1, 2}}), 4);
    }
}

/*
 * Brute Force:
 * I build the whole matrix, apply every row and column increment directly, and
 * then count the cells that finish with odd values.
 *
 * Time Complexity: O(q * (m + n) + m * n), where q is the number of updates.
 * Space Complexity: O(m * n), because the full matrix is stored.
 *
 * Optimal Interview Solution:
 * I track only whether each row and column has been incremented an odd number
 * of times. A cell is odd when exactly one of its row or column parity flags is
 * odd, so the final count can be computed from the number of odd rows and
 * columns.
 *
 * Time Complexity: O(q + m + n), where q is the number of updates.
 * Space Complexity: O(m + n), because only row and column parity arrays are
 * stored.
 */
