class MinimumTimeVisitingAllPoints {
    public int bruteForceMinTimeToVisitAllPoints(int[][] points) {
        int seconds = 0;

        for (int i = 1; i < points.length; i++) {
            int currentX = points[i - 1][0];
            int currentY = points[i - 1][1];
            int targetX = points[i][0];
            int targetY = points[i][1];

            while (currentX != targetX || currentY != targetY) {
                if (currentX < targetX) {
                    currentX++;
                } else if (currentX > targetX) {
                    currentX--;
                }

                if (currentY < targetY) {
                    currentY++;
                } else if (currentY > targetY) {
                    currentY--;
                }

                seconds++;
            }
        }

        return seconds;
    }

    public int minTimeToVisitAllPoints(int[][] points) {
        int seconds = 0;

        for (int i = 1; i < points.length; i++) {
            int xDistance = Math.abs(points[i][0] - points[i - 1][0]);
            int yDistance = Math.abs(points[i][1] - points[i - 1][1]);
            seconds += Math.max(xDistance, yDistance);
        }

        return seconds;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MinimumTimeVisitingAllPoints solution = new MinimumTimeVisitingAllPoints();

        check("brute force sample", solution.bruteForceMinTimeToVisitAllPoints(
                new int[][] {{1, 1}, {3, 4}, {-1, 0}}), 7);
        check("brute force horizontal and vertical", solution.bruteForceMinTimeToVisitAllPoints(
                new int[][] {{3, 2}, {-2, 2}}), 5);

        check("sample", solution.minTimeToVisitAllPoints(
                new int[][] {{1, 1}, {3, 4}, {-1, 0}}), 7);
        check("horizontal and vertical", solution.minTimeToVisitAllPoints(
                new int[][] {{3, 2}, {-2, 2}}), 5);
        check("single point", solution.minTimeToVisitAllPoints(
                new int[][] {{0, 0}}), 0);
        check("negative coordinates", solution.minTimeToVisitAllPoints(
                new int[][] {{-3, -4}, {-1, -1}, {2, -5}}), 7);
    }
}

/*
 * Brute Force:
 * I walk from each point to the next one second at a time, moving one step in
 * the x direction and one step in the y direction whenever either coordinate
 * still needs to change.
 *
 * Time Complexity: O(t), where t is the total number of seconds required to
 * visit all points.
 * Space Complexity: O(1), because only coordinate counters are stored.
 *
 * Optimal Interview Solution:
 * I use the fact that one second can change x, y, or both by one. The minimum
 * time between two points is therefore the larger of the x-distance and
 * y-distance, and summing that value for each adjacent pair gives the answer.
 *
 * Time Complexity: O(n), where n is the number of points.
 * Space Complexity: O(1), because the running total is the only extra state.
 */
