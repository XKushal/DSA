import java.util.Arrays;

class ImageSmoother {
    public int[][] bruteForceImageSmoother(int[][] img) {
        int rows = img.length;
        int cols = img[0].length;
        int[][] smooth = new int[rows][cols];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int sum = 0;
                int count = 0;

                for (int nextRow = 0; nextRow < rows; nextRow++) {
                    for (int nextCol = 0; nextCol < cols; nextCol++) {
                        if (Math.abs(nextRow - row) <= 1 && Math.abs(nextCol - col) <= 1) {
                            sum += img[nextRow][nextCol];
                            count++;
                        }
                    }
                }

                smooth[row][col] = sum / count;
            }
        }

        return smooth;
    }

    public int[][] imageSmoother(int[][] img) {
        int rows = img.length;
        int cols = img[0].length;
        int[][] smooth = new int[rows][cols];
        int[] directions = {-1, 0, 1};

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int sum = 0;
                int count = 0;

                for (int rowOffset : directions) {
                    for (int colOffset : directions) {
                        int nextRow = row + rowOffset;
                        int nextCol = col + colOffset;

                        if (nextRow >= 0 && nextRow < rows && nextCol >= 0 && nextCol < cols) {
                            sum += img[nextRow][nextCol];
                            count++;
                        }
                    }
                }

                smooth[row][col] = sum / count;
            }
        }

        return smooth;
    }

    private static void check(String name, int[][] actual, int[][] expected) {
        if (!Arrays.deepEquals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.deepToString(expected)
                    + " but got " + Arrays.deepToString(actual));
        }
    }

    private static int[][] matrix(int[]... rows) {
        return rows;
    }

    public static void main(String[] args) {
        ImageSmoother solution = new ImageSmoother();

        check("brute force smooths square image",
                solution.bruteForceImageSmoother(matrix(
                        new int[] {1, 1, 1},
                        new int[] {1, 0, 1},
                        new int[] {1, 1, 1})),
                matrix(
                        new int[] {0, 0, 0},
                        new int[] {0, 0, 0},
                        new int[] {0, 0, 0}));
        check("brute force handles single pixel",
                solution.bruteForceImageSmoother(matrix(new int[] {100})),
                matrix(new int[] {100}));

        check("smooths square image",
                solution.imageSmoother(matrix(
                        new int[] {1, 1, 1},
                        new int[] {1, 0, 1},
                        new int[] {1, 1, 1})),
                matrix(
                        new int[] {0, 0, 0},
                        new int[] {0, 0, 0},
                        new int[] {0, 0, 0}));
        check("handles varying values",
                solution.imageSmoother(matrix(
                        new int[] {100, 200, 100},
                        new int[] {200, 50, 200},
                        new int[] {100, 200, 100})),
                matrix(
                        new int[] {137, 141, 137},
                        new int[] {141, 138, 141},
                        new int[] {137, 141, 137}));
        check("handles single row",
                solution.imageSmoother(matrix(new int[] {2, 3, 4})),
                matrix(new int[] {2, 3, 3}));
        check("handles single pixel",
                solution.imageSmoother(matrix(new int[] {100})),
                matrix(new int[] {100}));
    }
}

/*
 * Brute Force:
 * I scan the entire image for every pixel and include only cells that are
 * within one row and one column of the current position.
 *
 * Time Complexity: O(m^2 * n^2), because each of the m * n pixels can scan the
 * whole image.
 * Space Complexity: O(m * n), because the smoothed image is stored separately.
 *
 * Optimal Interview Solution:
 * I only visit the at-most nine neighboring positions around each pixel and
 * average the valid cells.
 *
 * Time Complexity: O(m * n), because each pixel checks a constant number of
 * neighboring cells.
 * Space Complexity: O(m * n), because the smoothed image is stored separately.
 */
