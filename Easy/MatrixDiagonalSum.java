class MatrixDiagonalSum {
    public int bruteForceDiagonalSum(int[][] mat) {
        int n = mat.length;
        boolean[][] counted = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            counted[i][i] = true;
            counted[i][n - 1 - i] = true;
        }

        int sum = 0;
        for (int row = 0; row < n; row++) {
            for (int col = 0; col < n; col++) {
                if (counted[row][col]) {
                    sum += mat[row][col];
                }
            }
        }

        return sum;
    }

    public int diagonalSum(int[][] mat) {
        int n = mat.length;
        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += mat[i][i];
            sum += mat[i][n - 1 - i];
        }

        if (n % 2 == 1) {
            int middle = n / 2;
            sum -= mat[middle][middle];
        }

        return sum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MatrixDiagonalSum solution = new MatrixDiagonalSum();

        check("brute force odd size", solution.bruteForceDiagonalSum(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }), 25);
        check("brute force even size", solution.bruteForceDiagonalSum(new int[][] {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        }), 8);

        check("odd size", solution.diagonalSum(new int[][] {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        }), 25);
        check("even size", solution.diagonalSum(new int[][] {
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 1, 1}
        }), 8);
        check("single cell", solution.diagonalSum(new int[][] {{7}}), 7);
        check("mixed values", solution.diagonalSum(new int[][] {
                {5, 4, 7, 9, 1},
                {2, 8, 6, 3, 4},
                {0, 1, 10, 2, 5},
                {9, 3, 4, 7, 6},
                {8, 2, 1, 0, 11}
        }), 56);
    }
}

/*
 * Brute Force:
 * I mark every cell that belongs to either diagonal, then scan the entire
 * matrix and add only the marked cells.
 *
 * Time Complexity: O(n^2), where n is the matrix side length.
 * Space Complexity: O(n^2), because a second matrix tracks counted cells.
 *
 * Optimal Interview Solution:
 * I add both diagonal values while walking the rows once. For odd-sized
 * matrices, the center cell belongs to both diagonals, so I subtract it once.
 *
 * Time Complexity: O(n), where n is the matrix side length.
 * Space Complexity: O(1), because only the running sum is stored.
 */
