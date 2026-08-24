import java.util.Arrays;

class RunningSumOf1dArray {
    public int[] bruteForceRunningSum(int[] nums) {
        int[] runningSums = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int sum = 0;
            for (int j = 0; j <= i; j++) {
                sum += nums[j];
            }
            runningSums[i] = sum;
        }

        return runningSums;
    }

    public int[] runningSum(int[] nums) {
        int[] runningSums = new int[nums.length];
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            runningSums[i] = sum;
        }

        return runningSums;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                + " but got " + Arrays.toString(actual));
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    public static void main(String[] args) {
        RunningSumOf1dArray solution = new RunningSumOf1dArray();

        check("brute force sample", solution.bruteForceRunningSum(nums(1, 2, 3, 4)), nums(1, 3, 6, 10));
        check("brute force repeated values", solution.bruteForceRunningSum(nums(1, 1, 1, 1, 1)),
            nums(1, 2, 3, 4, 5));

        check("sample", solution.runningSum(nums(1, 2, 3, 4)), nums(1, 3, 6, 10));
        check("repeated values", solution.runningSum(nums(1, 1, 1, 1, 1)), nums(1, 2, 3, 4, 5));
        check("mixed values", solution.runningSum(nums(3, 1, 2, 10, 1)), nums(3, 4, 6, 16, 17));
        check("single value", solution.runningSum(nums(7)), nums(7));
    }
}

/*
 * Brute Force:
 * I compute each position's running sum by scanning from the beginning of the
 * array through that position.
 *
 * Time Complexity: O(n^2), because each index may scan the values before it.
 * Space Complexity: O(n), because the returned running sum array is stored.
 *
 * Optimal Interview Solution:
 * I keep one rolling sum while scanning left to right, then store that sum at
 * each position.
 *
 * Time Complexity: O(n), because each value is processed once.
 * Space Complexity: O(n), because the returned running sum array is stored.
 */
