class CountNegativeNumbersInASortedMatrix {
    public int bruteForceCountNegatives(int[][] grid) {
        int negatives = 0;

        for (int[] row : grid) {
            for (int value : row) {
                if (value < 0) {
                    negatives++;
                }
            }
        }

        return negatives;
    }

    public int countNegatives(int[][] grid) {
        int rows = grid.length;
        int columns = grid[0].length;
        int row = 0;
        int column = columns - 1;
        int negatives = 0;

        while (row < rows && column >= 0) {
            if (grid[row][column] < 0) {
                negatives += rows - row;
                column--;
            } else {
                row++;
            }
        }

        return negatives;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountNegativeNumbersInASortedMatrix solution = new CountNegativeNumbersInASortedMatrix();

        check("brute force counts mixed matrix",
                solution.bruteForceCountNegatives(new int[][] {
                        {4, 3, 2, -1},
                        {3, 2, 1, -1},
                        {1, 1, -1, -2},
                        {-1, -1, -2, -3}
                }), 8);
        check("brute force counts no negatives",
                solution.bruteForceCountNegatives(new int[][] {{3, 2}, {1, 0}}), 0);
        check("brute force counts all negatives",
                solution.bruteForceCountNegatives(new int[][] {{-1, -2}, {-3, -4}}), 4);

        check("counts mixed matrix",
                solution.countNegatives(new int[][] {
                        {4, 3, 2, -1},
                        {3, 2, 1, -1},
                        {1, 1, -1, -2},
                        {-1, -1, -2, -3}
                }), 8);
        check("counts rectangular matrix",
                solution.countNegatives(new int[][] {
                        {5, 1, 0},
                        {-5, -5, -5}
                }), 3);
        check("counts no negatives", solution.countNegatives(new int[][] {{3, 2}, {1, 0}}), 0);
        check("counts all negatives", solution.countNegatives(new int[][] {{-1, -2}, {-3, -4}}), 4);
    }
}

/*
 * Brute Force:
 * I inspect every value in the matrix and count each negative number directly.
 *
 * Time Complexity: O(m * n), because every cell is visited once.
 * Space Complexity: O(1), because only the running count is stored.
 *
 * Optimal Interview Solution:
 * I start from the top-right corner. When a value is negative, every value below
 * it in that column is also negative, so I count that whole suffix and move
 * left. Otherwise, I move down to find smaller values.
 *
 * Time Complexity: O(m + n), because each step moves one row down or one column
 * left.
 * Space Complexity: O(1), because only pointers and the running count are
 * stored.
 */
