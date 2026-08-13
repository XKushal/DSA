class HeightChecker {
    public int bruteForceHeightChecker(int[] heights) {
        int[] expected = copyOf(heights);

        for (int i = 0; i < expected.length; i++) {
            int minIndex = i;

            for (int j = i + 1; j < expected.length; j++) {
                if (expected[j] < expected[minIndex]) {
                    minIndex = j;
                }
            }

            int temp = expected[i];
            expected[i] = expected[minIndex];
            expected[minIndex] = temp;
        }

        return countMismatches(heights, expected);
    }

    public int heightChecker(int[] heights) {
        int[] frequencies = new int[101];

        for (int height : heights) {
            frequencies[height]++;
        }

        int mismatches = 0;
        int expectedHeight = 1;

        for (int height : heights) {
            while (frequencies[expectedHeight] == 0) {
                expectedHeight++;
            }

            if (height != expectedHeight) {
                mismatches++;
            }

            frequencies[expectedHeight]--;
        }

        return mismatches;
    }

    private int countMismatches(int[] heights, int[] expected) {
        int mismatches = 0;

        for (int i = 0; i < heights.length; i++) {
            if (heights[i] != expected[i]) {
                mismatches++;
            }
        }

        return mismatches;
    }

    private int[] copyOf(int[] nums) {
        int[] copy = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            copy[i] = nums[i];
        }

        return copy;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] heights(int... values) {
        return values;
    }

    public static void main(String[] args) {
        HeightChecker solution = new HeightChecker();

        check("brute force mixed order", solution.bruteForceHeightChecker(heights(1, 1, 4, 2, 1, 3)), 3);
        check("brute force already sorted", solution.bruteForceHeightChecker(heights(1, 2, 3, 4, 5)), 0);
        check("brute force reversed", solution.bruteForceHeightChecker(heights(5, 4, 3, 2, 1)), 4);

        check("mixed order", solution.heightChecker(heights(1, 1, 4, 2, 1, 3)), 3);
        check("already sorted", solution.heightChecker(heights(1, 2, 3, 4, 5)), 0);
        check("reversed", solution.heightChecker(heights(5, 4, 3, 2, 1)), 4);
        check("duplicates stay aligned", solution.heightChecker(heights(1, 2, 2, 3, 3)), 0);
        check("all same height", solution.heightChecker(heights(7, 7, 7)), 0);
    }
}

/*
 * Brute Force:
 * I copy the heights, sort that copy with selection sort, and count positions
 * where the original line differs from the sorted expectation.
 *
 * Time Complexity: O(n^2), because selection sort compares each pair of
 * positions in the copied array.
 * Space Complexity: O(n), because the expected ordering is stored in a copy.
 *
 * Optimal Interview Solution:
 * Heights are bounded from 1 through 100, so I count each height and walk the
 * original array while consuming the next expected sorted height.
 *
 * Time Complexity: O(n + k), where k is the bounded height range.
 * Space Complexity: O(k), because the frequency array stores one counter per
 * possible height.
 */
