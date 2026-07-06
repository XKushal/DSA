class MaximumSubarray {
    public int bruteForceMaxSubArray(int[] nums) {
        int bestSum = nums[0];

        for (int start = 0; start < nums.length; start++) {
            int currentSum = 0;

            for (int end = start; end < nums.length; end++) {
                currentSum += nums[end];
                bestSum = Math.max(bestSum, currentSum);
            }
        }

        return bestSum;
    }

    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int bestSum = nums[0];

        for (int index = 1; index < nums.length; index++) {
            currentSum = Math.max(nums[index], currentSum + nums[index]);
            bestSum = Math.max(bestSum, currentSum);
        }

        return bestSum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MaximumSubarray solution = new MaximumSubarray();

        check("brute force finds best middle range",
                solution.bruteForceMaxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);

        check("finds best middle range", solution.maxSubArray(new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4}), 6);
        check("handles one element", solution.maxSubArray(new int[] {1}), 1);
        check("chooses least negative value", solution.maxSubArray(new int[] {-3, -2, -5}), -2);
        check("uses the entire array", solution.maxSubArray(new int[] {5, 4, -1, 7, 8}), 23);
    }
}

/*
 * Brute Force:
 * I start a subarray at every index and extend it to every possible ending
 * index while tracking the largest sum.
 *
 * Time Complexity: O(n^2), because every start and end index pair is examined.
 * Space Complexity: O(1), because only running and best sums are stored.
 *
 * Optimal Interview Solution:
 * I use Kadane's algorithm. At each index, I either extend the current subarray
 * or start a new one, then update the best sum seen so far.
 *
 * Time Complexity: O(n), because the array is scanned once.
 * Space Complexity: O(1), because only the current and best sums are stored.
 */
