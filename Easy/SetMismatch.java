import java.util.Arrays;

class SetMismatch {
    public int[] bruteForceFindErrorNums(int[] nums) {
        int duplicate = -1;
        int missing = -1;

        for (int value = 1; value <= nums.length; value++) {
            int count = 0;

            for (int num : nums) {
                if (num == value) {
                    count++;
                }
            }

            if (count == 2) {
                duplicate = value;
            } else if (count == 0) {
                missing = value;
            }
        }

        return new int[] {duplicate, missing};
    }

    public int[] findErrorNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[] {nums[i], i + 1};
            }
        }

        return new int[] {-1, -1};
    }

    private void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        SetMismatch solution = new SetMismatch();

        check("brute force handles middle duplicate",
                solution.bruteForceFindErrorNums(values(1, 2, 2, 4)), values(2, 3));
        check("brute force handles missing first value",
                solution.bruteForceFindErrorNums(values(2, 2)), values(2, 1));
        check("brute force handles duplicate at end",
                solution.bruteForceFindErrorNums(values(1, 1, 3, 4, 5)), values(1, 2));

        check("handles middle duplicate", solution.findErrorNums(values(1, 2, 2, 4)), values(2, 3));
        check("handles missing first value", solution.findErrorNums(values(2, 2)), values(2, 1));
        check("handles duplicate at end", solution.findErrorNums(values(1, 1, 3, 4, 5)), values(1, 2));
        check("handles missing last value", solution.findErrorNums(values(1, 2, 3, 3)), values(3, 4));
    }
}

/*
 * Brute Force:
 * I count how often every expected value from 1 through n appears. The value
 * seen twice is the duplicate, and the value never seen is missing.
 *
 * Time Complexity: O(n^2), because each expected value scans the full array.
 * Space Complexity: O(1), because only the duplicate and missing values are
 * tracked.
 *
 * Optimal Interview Solution:
 * I place each value at its matching index with cyclic swaps. After that, the
 * only index whose value does not match reveals both the duplicate and the
 * missing value.
 *
 * Time Complexity: O(n), because each value is moved into place at most once.
 * Space Complexity: O(1), because the array is rearranged in place.
 */
