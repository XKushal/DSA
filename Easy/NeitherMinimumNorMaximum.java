class NeitherMinimumNorMaximum {
    public int bruteForceFindNonMinOrMax(int[] nums) {
        for (int candidate : nums) {
            boolean hasSmaller = false;
            boolean hasGreater = false;

            for (int value : nums) {
                if (value < candidate) {
                    hasSmaller = true;
                } else if (value > candidate) {
                    hasGreater = true;
                }
            }

            if (hasSmaller && hasGreater) {
                return candidate;
            }
        }

        return -1;
    }

    public int findNonMinOrMax(int[] nums) {
        if (nums.length < 3) {
            return -1;
        }

        int minimum = nums[0];
        int maximum = nums[0];

        for (int value : nums) {
            minimum = Math.min(minimum, value);
            maximum = Math.max(maximum, value);
        }

        for (int value : nums) {
            if (value != minimum && value != maximum) {
                return value;
            }
        }

        return -1;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NeitherMinimumNorMaximum solution = new NeitherMinimumNorMaximum();

        check("brute force sample one", solution.bruteForceFindNonMinOrMax(new int[] {3, 2, 1, 4}), 3);
        check("brute force sample two", solution.bruteForceFindNonMinOrMax(new int[] {1, 2}), -1);

        check("sample one", solution.findNonMinOrMax(new int[] {3, 2, 1, 4}), 3);
        check("sample two", solution.findNonMinOrMax(new int[] {1, 2}), -1);
        check("three values", solution.findNonMinOrMax(new int[] {2, 1, 3}), 2);
        check("middle at end", solution.findNonMinOrMax(new int[] {9, 1, 7}), 7);
        check("single value", solution.findNonMinOrMax(new int[] {5}), -1);
    }
}

/*
 * Brute Force:
 * I test each number as a candidate and scan the array to see whether another
 * value is smaller and another value is greater.
 *
 * Time Complexity: O(n^2), where n is the number of values.
 * Space Complexity: O(1), because only scalar flags are stored.
 *
 * Optimal Interview Solution:
 * I find the minimum and maximum values in one pass, then return the first
 * value that is neither of them.
 *
 * Time Complexity: O(n), where n is the number of values.
 * Space Complexity: O(1), because only the min and max values are stored.
 */
