import java.util.Arrays;

class MaximizeSumOfArrayAfterKNegations {
    public int bruteForceLargestSumAfterKNegations(int[] nums, int k) {
        for (int flips = 0; flips < k; flips++) {
            int minIndex = 0;

            for (int i = 1; i < nums.length; i++) {
                if (nums[i] < nums[minIndex]) {
                    minIndex = i;
                }
            }

            nums[minIndex] = -nums[minIndex];
        }

        return sum(nums);
    }

    public int largestSumAfterKNegations(int[] nums, int k) {
        Arrays.sort(nums);
        int minAbsoluteValue = Integer.MAX_VALUE;
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }

            minAbsoluteValue = Math.min(minAbsoluteValue, Math.abs(nums[i]));
            total += nums[i];
        }

        if (k % 2 == 1) {
            total -= 2 * minAbsoluteValue;
        }

        return total;
    }

    private int sum(int[] nums) {
        int total = 0;

        for (int num : nums) {
            total += num;
        }

        return total;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        MaximizeSumOfArrayAfterKNegations solution = new MaximizeSumOfArrayAfterKNegations();

        check("brute force flips one negative", solution.bruteForceLargestSumAfterKNegations(values(4, 2, 3), 1), 5);
        check("brute force handles zero", solution.bruteForceLargestSumAfterKNegations(values(3, -1, 0, 2), 3), 6);
        check("brute force uses leftover odd flip", solution.bruteForceLargestSumAfterKNegations(values(2, -3, -1, 5, -4), 2), 13);

        check("flips one negative", solution.largestSumAfterKNegations(values(4, 2, 3), 1), 5);
        check("handles zero", solution.largestSumAfterKNegations(values(3, -1, 0, 2), 3), 6);
        check("uses leftover odd flip", solution.largestSumAfterKNegations(values(2, -3, -1, 5, -4), 2), 13);
        check("adjusts smallest absolute value", solution.largestSumAfterKNegations(values(-8, 3, -5, -3, -5, -2), 6), 22);
    }
}

/*
 * Brute Force:
 * I repeat the operation k times, each time finding the current minimum value
 * and negating it before summing the final array.
 *
 * Time Complexity: O(k * n), because each operation scans the full array.
 * Space Complexity: O(1), because the flips are done in place.
 *
 * Optimal Interview Solution:
 * I sort the numbers so negative values can be flipped first. After the scan,
 * an odd number of remaining flips is handled by subtracting twice the smallest
 * absolute value from the sum.
 *
 * Time Complexity: O(n log n), because the array is sorted before one scan.
 * Space Complexity: O(1), ignoring the sorting implementation's internal space.
 */
