import java.util.Arrays;

class HowManyNumbersAreSmallerThanTheCurrentNumber {
    public int[] bruteForceSmallerNumbersThanCurrent(int[] nums) {
        int[] smallerCounts = new int[nums.length];

        for (int i = 0; i < nums.length; i++) {
            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] < nums[i]) {
                    count++;
                }
            }

            smallerCounts[i] = count;
        }

        return smallerCounts;
    }

    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] frequencies = new int[101];

        for (int num : nums) {
            frequencies[num]++;
        }

        int smallerBefore = 0;
        for (int i = 0; i < frequencies.length; i++) {
            int currentFrequency = frequencies[i];
            frequencies[i] = smallerBefore;
            smallerBefore += currentFrequency;
        }

        int[] smallerCounts = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            smallerCounts[i] = frequencies[nums[i]];
        }

        return smallerCounts;
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
        HowManyNumbersAreSmallerThanTheCurrentNumber solution =
            new HowManyNumbersAreSmallerThanTheCurrentNumber();

        check("brute force mixed values", solution.bruteForceSmallerNumbersThanCurrent(nums(8, 1, 2, 2, 3)),
            nums(4, 0, 1, 1, 3));
        check("brute force all equal", solution.bruteForceSmallerNumbersThanCurrent(nums(7, 7, 7, 7)),
            nums(0, 0, 0, 0));

        check("mixed values", solution.smallerNumbersThanCurrent(nums(8, 1, 2, 2, 3)), nums(4, 0, 1, 1, 3));
        check("descending values", solution.smallerNumbersThanCurrent(nums(6, 5, 4, 8)), nums(2, 1, 0, 3));
        check("all equal", solution.smallerNumbersThanCurrent(nums(7, 7, 7, 7)), nums(0, 0, 0, 0));
        check("includes zero", solution.smallerNumbersThanCurrent(nums(0, 100, 50, 50)), nums(0, 3, 1, 1));
    }
}

/*
 * Brute Force:
 * I compare every number with every other number and count how many values are
 * strictly smaller than the current one.
 *
 * Time Complexity: O(n^2), because each number is compared with every number.
 * Space Complexity: O(n), because the result array stores one count per input.
 *
 * Optimal Interview Solution:
 * I count each value from 0 through 100, convert those counts into the number
 * of smaller values seen before each score, then map each input to that prefix
 * count.
 *
 * Time Complexity: O(n + k), where k is the fixed value range from 0 to 100.
 * Space Complexity: O(n + k), because the result and frequency arrays are used.
 */
