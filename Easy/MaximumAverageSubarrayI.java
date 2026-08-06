class MaximumAverageSubarrayI {
    public double bruteForceFindMaxAverage(int[] nums, int k) {
        double best = -Double.MAX_VALUE;

        for (int start = 0; start <= nums.length - k; start++) {
            int sum = 0;

            for (int end = start; end < start + k; end++) {
                sum += nums[end];
            }

            best = Math.max(best, (double) sum / k);
        }

        return best;
    }

    public double findMaxAverage(int[] nums, int k) {
        int windowSum = 0;

        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }

        int bestSum = windowSum;

        for (int i = k; i < nums.length; i++) {
            windowSum += nums[i] - nums[i - k];
            bestSum = Math.max(bestSum, windowSum);
        }

        return (double) bestSum / k;
    }

    private static void check(String name, double actual, double expected) {
        double tolerance = 0.00001;

        if (Math.abs(actual - expected) > tolerance) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        MaximumAverageSubarrayI solution = new MaximumAverageSubarrayI();

        check("brute force handles mixed values",
                solution.bruteForceFindMaxAverage(values(1, 12, -5, -6, 50, 3), 4), 12.75);
        check("brute force handles single element window",
                solution.bruteForceFindMaxAverage(values(5), 1), 5.0);
        check("brute force handles negative values",
                solution.bruteForceFindMaxAverage(values(-3, -5, -1, -2), 2), -1.5);

        check("handles mixed values", solution.findMaxAverage(values(1, 12, -5, -6, 50, 3), 4), 12.75);
        check("handles single element window", solution.findMaxAverage(values(5), 1), 5.0);
        check("handles negative values", solution.findMaxAverage(values(-3, -5, -1, -2), 2), -1.5);
        check("handles full array window", solution.findMaxAverage(values(4, 2, 1, 3), 4), 2.5);
    }
}

/*
 * Brute Force:
 * I calculate the sum for every contiguous subarray of length k and keep the
 * largest average found.
 *
 * Time Complexity: O(n * k), because each possible window sums k values.
 * Space Complexity: O(1), because only the current sum and best average are
 * stored.
 *
 * Optimal Interview Solution:
 * I compute the first window sum once, then slide the window by removing the
 * outgoing value and adding the incoming value.
 *
 * Time Complexity: O(n), because each value is added and removed at most once.
 * Space Complexity: O(1), because only the current and best window sums are
 * stored.
 */
