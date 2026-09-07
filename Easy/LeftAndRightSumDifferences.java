import java.util.Arrays;

class LeftAndRightSumDifferences {
    public int[] bruteForceLeftRightDifference(int[] nums) {
        int[] answer = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int leftSum = 0;
            for (int left = 0; left < i; left++) {
                leftSum += nums[left];
            }

            int rightSum = 0;
            for (int right = i + 1; right < nums.length; right++) {
                rightSum += nums[right];
            }

            answer[i] = Math.abs(leftSum - rightSum);
        }

        return answer;
    }

    public int[] leftRightDifference(int[] nums) {
        int rightSum = 0;
        for (int num : nums) {
            rightSum += num;
        }

        int leftSum = 0;
        int[] answer = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            rightSum -= nums[i];
            answer[i] = Math.abs(leftSum - rightSum);
            leftSum += nums[i];
        }

        return answer;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected "
                    + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        LeftAndRightSumDifferences solution = new LeftAndRightSumDifferences();

        check("brute force sample", solution.bruteForceLeftRightDifference(
                new int[] {10, 4, 8, 3}), new int[] {15, 1, 11, 22});
        check("brute force single value", solution.bruteForceLeftRightDifference(
                new int[] {1}), new int[] {0});

        check("sample", solution.leftRightDifference(
                new int[] {10, 4, 8, 3}), new int[] {15, 1, 11, 22});
        check("single value", solution.leftRightDifference(
                new int[] {1}), new int[] {0});
        check("increasing values", solution.leftRightDifference(
                new int[] {1, 2, 3, 4}), new int[] {9, 6, 1, 6});
        check("equal sides", solution.leftRightDifference(
                new int[] {5, 1, 10, 1, 5}), new int[] {17, 11, 0, 11, 17});
    }
}

/*
 * Brute Force:
 * I recompute the sum to the left and the sum to the right for every index,
 * then store the absolute difference between those two sums.
 *
 * Time Complexity: O(n^2), where n is the number of values.
 * Space Complexity: O(n), because the answer array is stored.
 *
 * Optimal Interview Solution:
 * I start with the full array as the right sum, then walk once through the
 * array while moving each number from the right sum into the left sum.
 *
 * Time Complexity: O(n), where n is the number of values.
 * Space Complexity: O(n), because the answer array is stored.
 */
