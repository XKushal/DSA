import java.util.Arrays;

class FlippingAnImage {
    public int[][] bruteForceFlipAndInvertImage(int[][] image) {
        int[][] flipped = new int[image.length][image[0].length];

        for (int row = 0; row < image.length; row++) {
            for (int col = 0; col < image[0].length; col++) {
                flipped[row][col] = image[row][image[0].length - 1 - col] ^ 1;
            }
        }

        return flipped;
    }

    public int[][] flipAndInvertImage(int[][] image) {
        for (int row = 0; row < image.length; row++) {
            int left = 0;
            int right = image[row].length - 1;

            while (left <= right) {
                int leftValue = image[row][left] ^ 1;
                int rightValue = image[row][right] ^ 1;

                image[row][left] = rightValue;
                image[row][right] = leftValue;

                left++;
                right--;
            }
        }

        return image;
    }

    private static void check(String name, int[][] actual, int[][] expected) {
        if (!Arrays.deepEquals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.deepToString(expected) + " but got " + Arrays.deepToString(actual));
        }
    }

    public static void main(String[] args) {
        FlippingAnImage solution = new FlippingAnImage();

        check("brute force flips and inverts odd width rows",
                solution.bruteForceFlipAndInvertImage(new int[][] {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}}),
                new int[][] {{1, 0, 0}, {0, 1, 0}, {1, 1, 1}});
        check("brute force flips and inverts even width rows",
                solution.bruteForceFlipAndInvertImage(new int[][] {{1, 1, 0, 0}, {1, 0, 0, 1}}),
                new int[][] {{1, 1, 0, 0}, {0, 1, 1, 0}});

        check("flips and inverts odd width rows",
                solution.flipAndInvertImage(new int[][] {{1, 1, 0}, {1, 0, 1}, {0, 0, 0}}),
                new int[][] {{1, 0, 0}, {0, 1, 0}, {1, 1, 1}});
        check("flips and inverts even width rows",
                solution.flipAndInvertImage(new int[][] {{1, 1, 0, 0}, {1, 0, 0, 1}}),
                new int[][] {{1, 1, 0, 0}, {0, 1, 1, 0}});
        check("handles a single cell", solution.flipAndInvertImage(new int[][] {{0}}), new int[][] {{1}});
    }
}

/*
 * Brute Force:
 * I allocate a new matrix, read each row from right to left, and invert every
 * bit while copying it into the result.
 *
 * Time Complexity: O(n), where n is the total number of cells.
 * Space Complexity: O(n), because the result matrix stores every cell.
 *
 * Optimal Interview Solution:
 * I use two pointers on each row, swap the mirrored cells, and invert both
 * values in the same pass.
 *
 * Time Complexity: O(n), where n is the total number of cells.
 * Space Complexity: O(1), because the image is modified in place.
 */
