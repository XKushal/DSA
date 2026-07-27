class ValidBoomerang {
    public boolean bruteForceIsBoomerang(int[][] points) {
        if (isSamePoint(points[0], points[1])
                || isSamePoint(points[0], points[2])
                || isSamePoint(points[1], points[2])) {
            return false;
        }

        if (points[0][0] == points[1][0] || points[0][0] == points[2][0]) {
            return points[0][0] != points[1][0] || points[0][0] != points[2][0];
        }

        double firstSlope = (double) (points[1][1] - points[0][1]) / (points[1][0] - points[0][0]);
        double secondSlope = (double) (points[2][1] - points[0][1]) / (points[2][0] - points[0][0]);

        return firstSlope != secondSlope;
    }

    public boolean isBoomerang(int[][] points) {
        int firstX = points[1][0] - points[0][0];
        int firstY = points[1][1] - points[0][1];
        int secondX = points[2][0] - points[0][0];
        int secondY = points[2][1] - points[0][1];

        return firstX * secondY != firstY * secondX;
    }

    private boolean isSamePoint(int[] first, int[] second) {
        return first[0] == second[0] && first[1] == second[1];
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ValidBoomerang solution = new ValidBoomerang();

        check("brute force accepts non-collinear points",
                solution.bruteForceIsBoomerang(new int[][] {{1, 1}, {2, 3}, {3, 2}}),
                true);
        check("brute force rejects duplicate points",
                solution.bruteForceIsBoomerang(new int[][] {{1, 1}, {1, 1}, {2, 3}}),
                false);

        check("accepts non-collinear points", solution.isBoomerang(new int[][] {{1, 1}, {2, 3}, {3, 2}}), true);
        check("rejects diagonal collinear points", solution.isBoomerang(new int[][] {{1, 1}, {2, 2}, {3, 3}}), false);
        check("rejects duplicate points", solution.isBoomerang(new int[][] {{0, 0}, {0, 0}, {1, 1}}), false);
        check("accepts vertical and off-line points", solution.isBoomerang(new int[][] {{0, 0}, {0, 2}, {1, 1}}), true);
    }
}

/*
 * Brute Force:
 * I first reject duplicate points, then compare slopes from the first point to
 * the other two points while handling vertical lines separately.
 *
 * Time Complexity: O(1), because there are always exactly three points.
 * Space Complexity: O(1).
 *
 * Optimal Interview Solution:
 * I compute the cross product of the vectors from the first point. A non-zero
 * cross product means the three points are not collinear, which also rejects
 * duplicate points because a zero-length vector gives a zero cross product.
 *
 * Time Complexity: O(1), because there are always exactly three points.
 * Space Complexity: O(1).
 */
