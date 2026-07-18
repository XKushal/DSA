class MonotonicArray {
    public boolean bruteForceIsMonotonic(int[] nums) {
        boolean nonDecreasing = true;
        boolean nonIncreasing = true;

        for (int left = 0; left < nums.length; left++) {
            for (int right = left + 1; right < nums.length; right++) {
                if (nums[left] > nums[right]) {
                    nonDecreasing = false;
                }

                if (nums[left] < nums[right]) {
                    nonIncreasing = false;
                }
            }
        }

        return nonDecreasing || nonIncreasing;
    }

    public boolean isMonotonic(int[] nums) {
        boolean nonDecreasing = true;
        boolean nonIncreasing = true;

        for (int index = 1; index < nums.length; index++) {
            if (nums[index - 1] > nums[index]) {
                nonDecreasing = false;
            }

            if (nums[index - 1] < nums[index]) {
                nonIncreasing = false;
            }
        }

        return nonDecreasing || nonIncreasing;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MonotonicArray solution = new MonotonicArray();

        check("brute force accepts increasing array",
                solution.bruteForceIsMonotonic(new int[] {1, 2, 2, 3}), true);
        check("brute force rejects direction change",
                solution.bruteForceIsMonotonic(new int[] {1, 3, 2}), false);

        check("accepts increasing array", solution.isMonotonic(new int[] {1, 2, 2, 3}), true);
        check("accepts decreasing array", solution.isMonotonic(new int[] {6, 5, 4, 4}), true);
        check("accepts all equal values", solution.isMonotonic(new int[] {2, 2, 2}), true);
        check("rejects valley shape", solution.isMonotonic(new int[] {1, 3, 2}), false);
        check("rejects peak shape", solution.isMonotonic(new int[] {3, 2, 4}), false);
        check("handles one value", solution.isMonotonic(new int[] {7}), true);
    }
}

/*
 * Brute Force:
 * I compare every ordered pair and track whether the array can still be
 * non-decreasing or non-increasing after seeing that pair.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because only two direction flags are stored.
 *
 * Optimal Interview Solution:
 * I make one pass over adjacent pairs and clear the impossible direction flags
 * as soon as a pair violates that direction.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because only two direction flags are stored.
 */
