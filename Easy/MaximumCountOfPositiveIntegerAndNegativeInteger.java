class MaximumCountOfPositiveIntegerAndNegativeInteger {
    public int bruteForceMaximumCount(int[] nums) {
        int negative = 0;
        int positive = 0;

        for (int num : nums) {
            if (num < 0) {
                negative++;
            } else if (num > 0) {
                positive++;
            }
        }

        return Math.max(negative, positive);
    }

    public int maximumCount(int[] nums) {
        int firstNonNegative = lowerBound(nums, 0);
        int firstPositive = upperBound(nums, 0);

        int negative = firstNonNegative;
        int positive = nums.length - firstPositive;

        return Math.max(negative, positive);
    }

    private int lowerBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private int upperBound(int[] nums, int target) {
        int left = 0;
        int right = nums.length;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return left;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumCountOfPositiveIntegerAndNegativeInteger solution =
                new MaximumCountOfPositiveIntegerAndNegativeInteger();

        check("brute force sample one", solution.bruteForceMaximumCount(new int[] {-2, -1, -1, 1, 2, 3}), 3);
        check("brute force sample two", solution.bruteForceMaximumCount(new int[] {-3, -2, -1, 0, 0, 1, 2}), 3);

        check("sample one", solution.maximumCount(new int[] {-2, -1, -1, 1, 2, 3}), 3);
        check("sample two", solution.maximumCount(new int[] {-3, -2, -1, 0, 0, 1, 2}), 3);
        check("sample three", solution.maximumCount(new int[] {5, 20, 66, 1314}), 4);
        check("only zeros", solution.maximumCount(new int[] {0, 0}), 0);
        check("more negatives", solution.maximumCount(new int[] {-5, -4, -3, 0, 1}), 3);
    }
}

/*
 * Brute Force:
 * I scan every value once and count how many numbers are negative and how many
 * are positive.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(1), because only counters are stored.
 *
 * Optimal Interview Solution:
 * Because nums is sorted, I use binary search to find the first non-negative
 * value and the first positive value. Those split points give both counts
 * directly.
 *
 * Time Complexity: O(log n), where n is the length of nums.
 * Space Complexity: O(1), because only index variables are used.
 */
