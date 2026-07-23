import java.util.Arrays;

class MatrixReshape {
    public int[][] bruteForceMatrixReshape(int[][] mat, int r, int c) {
        int rows = mat.length;
        int cols = mat[0].length;

        if (rows * cols != r * c) {
            return mat;
        }

        int[] values = new int[rows * cols];
        int writeIndex = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                values[writeIndex] = mat[row][col];
                writeIndex++;
            }
        }

        int[][] reshaped = new int[r][c];
        for (int index = 0; index < values.length; index++) {
            reshaped[index / c][index % c] = values[index];
        }

        return reshaped;
    }

    public int[][] matrixReshape(int[][] mat, int r, int c) {
        int rows = mat.length;
        int cols = mat[0].length;

        if (rows * cols != r * c) {
            return mat;
        }

        int[][] reshaped = new int[r][c];

        for (int index = 0; index < rows * cols; index++) {
            reshaped[index / c][index % c] = mat[index / cols][index % cols];
        }

        return reshaped;
    }

    private static void check(String name, int[][] actual, int[][] expected) {
        if (!Arrays.deepEquals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.deepToString(expected) + " but got " + Arrays.deepToString(actual));
        }
    }

    public static void main(String[] args) {
        MatrixReshape solution = new MatrixReshape();

        check("brute force reshapes one row into two rows",
                solution.bruteForceMatrixReshape(new int[][] {{1, 2, 3, 4}}, 2, 2),
                new int[][] {{1, 2}, {3, 4}});
        check("brute force returns original dimensions when reshape is invalid",
                solution.bruteForceMatrixReshape(new int[][] {{1, 2}, {3, 4}}, 3, 2),
                new int[][] {{1, 2}, {3, 4}});

        check("reshapes two rows into one row",
                solution.matrixReshape(new int[][] {{1, 2}, {3, 4}}, 1, 4),
                new int[][] {{1, 2, 3, 4}});
        check("reshapes one row into two rows",
                solution.matrixReshape(new int[][] {{1, 2, 3, 4}}, 2, 2),
                new int[][] {{1, 2}, {3, 4}});
        check("returns original matrix when reshape is invalid",
                solution.matrixReshape(new int[][] {{1, 2}, {3, 4}}, 2, 3),
                new int[][] {{1, 2}, {3, 4}});
    }
}

/*
 * Brute Force:
 * I copy every value into a one-dimensional array, then refill the reshaped
 * matrix from that intermediate array.
 *
 * Time Complexity: O(m * n), where m and n are the original matrix dimensions.
 * Space Complexity: O(m * n), because the flattened array stores every value.
 *
 * Optimal Interview Solution:
 * I map each linear index directly from its original row and column to its new
 * row and column, so no intermediate flattened array is needed.
 *
 * Time Complexity: O(m * n), where m and n are the original matrix dimensions.
 * Space Complexity: O(1) extra space beyond the required reshaped matrix.
 */
