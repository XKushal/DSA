import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

class FloodFill {
    public int[][] bruteForceFloodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];
        boolean[][] shouldFill = new boolean[image.length][image[0].length];
        Queue<int[]> cells = new ArrayDeque<>();
        cells.offer(new int[] {sr, sc});
        shouldFill[sr][sc] = true;

        while (!cells.isEmpty()) {
            int[] cell = cells.poll();
            int row = cell[0];
            int col = cell[1];

            addMatchingNeighbor(image, shouldFill, cells, row - 1, col, originalColor);
            addMatchingNeighbor(image, shouldFill, cells, row + 1, col, originalColor);
            addMatchingNeighbor(image, shouldFill, cells, row, col - 1, originalColor);
            addMatchingNeighbor(image, shouldFill, cells, row, col + 1, originalColor);
        }

        int[][] filled = copyImage(image);
        for (int row = 0; row < filled.length; row++) {
            for (int col = 0; col < filled[0].length; col++) {
                if (shouldFill[row][col]) {
                    filled[row][col] = color;
                }
            }
        }

        return filled;
    }

    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int originalColor = image[sr][sc];

        if (originalColor == color) {
            return image;
        }

        fill(image, sr, sc, originalColor, color);
        return image;
    }

    private void fill(int[][] image, int row, int col, int originalColor, int color) {
        if (row < 0 || row == image.length || col < 0 || col == image[0].length || image[row][col] != originalColor) {
            return;
        }

        image[row][col] = color;

        fill(image, row - 1, col, originalColor, color);
        fill(image, row + 1, col, originalColor, color);
        fill(image, row, col - 1, originalColor, color);
        fill(image, row, col + 1, originalColor, color);
    }

    private void addMatchingNeighbor(int[][] image, boolean[][] visited, Queue<int[]> cells, int row, int col,
            int originalColor) {
        if (row < 0 || row == image.length || col < 0 || col == image[0].length || visited[row][col]
                || image[row][col] != originalColor) {
            return;
        }

        visited[row][col] = true;
        cells.offer(new int[] {row, col});
    }

    private static int[][] copyImage(int[][] image) {
        int[][] copy = new int[image.length][image[0].length];

        for (int row = 0; row < image.length; row++) {
            copy[row] = Arrays.copyOf(image[row], image[row].length);
        }

        return copy;
    }

    private static void check(String name, int[][] actual, int[][] expected) {
        if (!Arrays.deepEquals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.deepToString(expected) + " but got " + Arrays.deepToString(actual));
        }
    }

    public static void main(String[] args) {
        FloodFill solution = new FloodFill();

        check("brute force fills connected region",
                solution.bruteForceFloodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2),
                new int[][] {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
        check("brute force leaves disconnected matching values unchanged",
                solution.bruteForceFloodFill(new int[][] {{0, 0, 0}, {0, 1, 1}}, 1, 1, 1),
                new int[][] {{0, 0, 0}, {0, 1, 1}});

        check("fills connected region",
                solution.floodFill(new int[][] {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}}, 1, 1, 2),
                new int[][] {{2, 2, 2}, {2, 2, 0}, {2, 0, 1}});
        check("returns image unchanged when color already matches",
                solution.floodFill(new int[][] {{0, 0, 0}, {0, 1, 1}}, 1, 1, 1),
                new int[][] {{0, 0, 0}, {0, 1, 1}});
        check("fills single cell image", solution.floodFill(new int[][] {{0}}, 0, 0, 2), new int[][] {{2}});
    }
}

/*
 * Brute Force:
 * I first discover every connected cell with the starting color using a queue
 * and a separate visited grid, then copy the image and recolor only those cells.
 *
 * Time Complexity: O(m * n), where m and n are the image dimensions.
 * Space Complexity: O(m * n), because the visited grid and queue can track every
 * cell.
 *
 * Optimal Interview Solution:
 * I recolor the image in place with depth-first search, using the color change
 * itself to mark cells that have already been visited.
 *
 * Time Complexity: O(m * n), where m and n are the image dimensions.
 * Space Complexity: O(m * n) in the worst case for the recursion stack.
 */
