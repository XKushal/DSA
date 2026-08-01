import java.util.Arrays;

class ConstructTheRectangle {
    public int[] bruteForceConstructRectangle(int area) {
        int bestLength = area;
        int bestWidth = 1;
        int smallestDifference = area - 1;

        for (int width = 1; width <= area; width++) {
            if (area % width != 0) {
                continue;
            }

            int length = area / width;

            if (length >= width && length - width < smallestDifference) {
                bestLength = length;
                bestWidth = width;
                smallestDifference = length - width;
            }
        }

        return new int[] {bestLength, bestWidth};
    }

    public int[] constructRectangle(int area) {
        for (int width = (int) Math.sqrt(area); width >= 1; width--) {
            if (area % width == 0) {
                return new int[] {area / width, width};
            }
        }

        return new int[] {area, 1};
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        ConstructTheRectangle solution = new ConstructTheRectangle();

        check("brute force handles simple area", solution.bruteForceConstructRectangle(4), new int[] {2, 2});
        check("brute force handles prime area", solution.bruteForceConstructRectangle(37), new int[] {37, 1});
        check("brute force chooses closest factors", solution.bruteForceConstructRectangle(122122), new int[] {427, 286});

        check("handles unit area", solution.constructRectangle(1), new int[] {1, 1});
        check("handles simple area", solution.constructRectangle(4), new int[] {2, 2});
        check("handles prime area", solution.constructRectangle(37), new int[] {37, 1});
        check("chooses closest factors", solution.constructRectangle(122122), new int[] {427, 286});
    }
}

/*
 * Brute Force:
 * I scan every possible width, keep the factor pair where length is at least
 * width, and update the answer when the pair has a smaller difference.
 *
 * Time Complexity: O(area), because every width from 1 through area is tested.
 * Space Complexity: O(1), because only the current best dimensions are stored.
 *
 * Optimal Interview Solution:
 * I start from the square root of the area and scan downward until the first
 * factor appears, which gives the closest valid width immediately.
 *
 * Time Complexity: O(sqrt(area)), because at most the square-root range is
 * scanned.
 * Space Complexity: O(1), because only loop counters and the returned pair are
 * stored.
 */
