class IslandPerimeter {
    public int bruteForceIslandPerimeter(int[][] grid) {
        int perimeter = 0;
        int[][] directions = {
                {-1, 0},
                {1, 0},
                {0, -1},
                {0, 1}
        };

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }

                for (int[] direction : directions) {
                    int nextRow = row + direction[0];
                    int nextCol = col + direction[1];

                    if (isWaterOrEdge(grid, nextRow, nextCol)) {
                        perimeter++;
                    }
                }
            }
        }

        return perimeter;
    }

    public int islandPerimeter(int[][] grid) {
        int landCells = 0;
        int sharedEdges = 0;

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                if (grid[row][col] == 0) {
                    continue;
                }

                landCells++;

                if (row > 0 && grid[row - 1][col] == 1) {
                    sharedEdges++;
                }

                if (col > 0 && grid[row][col - 1] == 1) {
                    sharedEdges++;
                }
            }
        }

        return landCells * 4 - sharedEdges * 2;
    }

    private boolean isWaterOrEdge(int[][] grid, int row, int col) {
        return row < 0 || row == grid.length
                || col < 0 || col == grid[row].length
                || grid[row][col] == 0;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        IslandPerimeter solution = new IslandPerimeter();

        check("brute force handles standard island",
                solution.bruteForceIslandPerimeter(new int[][] {
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {1, 1, 0, 0}
                }), 16);
        check("brute force handles single land cell",
                solution.bruteForceIslandPerimeter(new int[][] {{1}}), 4);

        check("handles standard island",
                solution.islandPerimeter(new int[][] {
                        {0, 1, 0, 0},
                        {1, 1, 1, 0},
                        {0, 1, 0, 0},
                        {1, 1, 0, 0}
                }), 16);
        check("handles single land cell", solution.islandPerimeter(new int[][] {{1}}), 4);
        check("handles horizontal strip", solution.islandPerimeter(new int[][] {{1, 1, 1}}), 8);
        check("handles solid rectangle",
                solution.islandPerimeter(new int[][] {
                        {1, 1},
                        {1, 1}
                }), 8);
    }
}

/*
 * Brute Force:
 * I visit every land cell and inspect all four directions. Each direction that
 * reaches water or the grid boundary contributes one side to the perimeter.
 *
 * Time Complexity: O(m * n), where m and n are the grid dimensions, because
 * each cell is visited and each land cell checks four constant-time sides.
 * Space Complexity: O(1), because the direction list has a fixed size.
 *
 * Optimal Interview Solution:
 * I count every land cell as four sides, then subtract two sides for each
 * shared edge with land above or to the left.
 *
 * Time Complexity: O(m * n), because each grid cell is processed once.
 * Space Complexity: O(1), because only counters are stored.
 */
