class CheckIfItIsAStraightLine {
    public boolean bruteForceCheckStraightLine(int[][] coordinates) {
        if (coordinates.length <= 2) {
            return true;
        }

        int[] first = coordinates[0];
        int[] second = coordinates[1];
        int deltaX = second[0] - first[0];
        int deltaY = second[1] - first[1];

        if (deltaX == 0) {
            for (int i = 2; i < coordinates.length; i++) {
                if (coordinates[i][0] != first[0]) {
                    return false;
                }
            }

            return true;
        }

        double slope = (double) deltaY / deltaX;

        for (int i = 2; i < coordinates.length; i++) {
            int currentDeltaX = coordinates[i][0] - first[0];
            int currentDeltaY = coordinates[i][1] - first[1];

            if ((double) currentDeltaY / currentDeltaX != slope) {
                return false;
            }
        }

        return true;
    }

    public boolean checkStraightLine(int[][] coordinates) {
        int startX = coordinates[0][0];
        int startY = coordinates[0][1];
        int deltaX = coordinates[1][0] - startX;
        int deltaY = coordinates[1][1] - startY;

        for (int i = 2; i < coordinates.length; i++) {
            int currentDeltaX = coordinates[i][0] - startX;
            int currentDeltaY = coordinates[i][1] - startY;

            if (deltaY * currentDeltaX != deltaX * currentDeltaY) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[][] points(int[]... coordinates) {
        return coordinates;
    }

    private static int[] point(int x, int y) {
        return new int[] { x, y };
    }

    public static void main(String[] args) {
        CheckIfItIsAStraightLine solution = new CheckIfItIsAStraightLine();

        check("brute force diagonal line", solution.bruteForceCheckStraightLine(points(
            point(1, 2), point(2, 3), point(3, 4), point(4, 5)
        )), true);
        check("brute force broken line", solution.bruteForceCheckStraightLine(points(
            point(1, 1), point(2, 2), point(3, 4), point(4, 5)
        )), false);
        check("brute force vertical line", solution.bruteForceCheckStraightLine(points(
            point(2, 1), point(2, 3), point(2, 5)
        )), true);

        check("diagonal line", solution.checkStraightLine(points(
            point(1, 2), point(2, 3), point(3, 4), point(4, 5), point(5, 6)
        )), true);
        check("broken line", solution.checkStraightLine(points(
            point(1, 1), point(2, 2), point(3, 4), point(4, 5)
        )), false);
        check("vertical line", solution.checkStraightLine(points(
            point(2, 1), point(2, 3), point(2, 5), point(2, 8)
        )), true);
        check("horizontal line", solution.checkStraightLine(points(
            point(-3, 4), point(0, 4), point(6, 4)
        )), true);
        check("two points", solution.checkStraightLine(points(
            point(0, 0), point(7, -2)
        )), true);
        check("negative slope", solution.checkStraightLine(points(
            point(0, 0), point(2, -2), point(5, -5), point(9, -9)
        )), true);
    }
}

/*
 * Brute Force:
 * I compute the slope from the first two points and compare every remaining
 * point against that slope, with a separate branch for vertical lines.
 *
 * Time Complexity: O(n), because each point is checked once.
 * Space Complexity: O(1), because only a few slope values are stored.
 *
 * Optimal Interview Solution:
 * I avoid division by comparing cross products from the first segment to every
 * other point, which handles vertical, horizontal, and negative slopes exactly.
 *
 * Time Complexity: O(n), because each point is checked once.
 * Space Complexity: O(1), because the check uses constant extra state.
 */
