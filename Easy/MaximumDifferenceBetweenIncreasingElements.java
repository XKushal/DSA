class MaximumDifferenceBetweenIncreasingElements {
    public int bruteForceMaximumDifference(int[] nums) {
        int best = -1;

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    best = Math.max(best, nums[j] - nums[i]);
                }
            }
        }

        return best;
    }

    public int maximumDifference(int[] nums) {
        int smallestBefore = nums[0];
        int best = -1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > smallestBefore) {
                best = Math.max(best, nums[i] - smallestBefore);
            } else {
                smallestBefore = nums[i];
            }
        }

        return best;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumDifferenceBetweenIncreasingElements solution =
                new MaximumDifferenceBetweenIncreasingElements();

        check("brute force sample", solution.bruteForceMaximumDifference(
                new int[] {7, 1, 5, 4}), 4);
        check("brute force decreasing", solution.bruteForceMaximumDifference(
                new int[] {9, 4, 3, 2}), -1);

        check("sample", solution.maximumDifference(new int[] {7, 1, 5, 4}), 4);
        check("decreasing values", solution.maximumDifference(new int[] {9, 4, 3, 2}), -1);
        check("later best pair", solution.maximumDifference(new int[] {1, 5, 2, 10}), 9);
        check("duplicate then increase", solution.maximumDifference(new int[] {4, 4, 6}), 2);
    }
}

/*
 * Brute Force:
 * I try every ordered pair of indices and keep the largest valid difference
 * where the earlier value is smaller than the later value.
 *
 * Time Complexity: O(n^2), where n is the number of values.
 * Space Complexity: O(1), because only the best difference is stored.
 *
 * Optimal Interview Solution:
 * I scan the array once while tracking the smallest value seen before the
 * current index. Each current value can then form the best pair ending there.
 *
 * Time Complexity: O(n), where n is the number of values.
 * Space Complexity: O(1), because only two integer trackers are stored.
 */
