import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class MinimumOperationsToMakeArrayValuesEqualToK {
    public int bruteForceMinOperations(int[] nums, int k) {
        int[] values = Arrays.copyOf(nums, nums.length);
        int operations = 0;

        while (true) {
            boolean allEqualToK = true;
            int maximum = Integer.MIN_VALUE;
            int secondMaximum = Integer.MIN_VALUE;

            for (int value : values) {
                if (value < k) {
                    return -1;
                }

                if (value != k) {
                    allEqualToK = false;
                }

                if (value > maximum) {
                    secondMaximum = maximum;
                    maximum = value;
                } else if (value < maximum && value > secondMaximum) {
                    secondMaximum = value;
                }
            }

            if (allEqualToK) {
                return operations;
            }

            int nextValue = secondMaximum == Integer.MIN_VALUE ? k : Math.max(secondMaximum, k);

            for (int i = 0; i < values.length; i++) {
                if (values[i] > nextValue) {
                    values[i] = nextValue;
                }
            }

            operations++;
        }
    }

    public int minOperations(int[] nums, int k) {
        Set<Integer> valuesAboveK = new HashSet<>();

        for (int value : nums) {
            if (value < k) {
                return -1;
            }

            if (value > k) {
                valuesAboveK.add(value);
            }
        }

        return valuesAboveK.size();
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MinimumOperationsToMakeArrayValuesEqualToK solution =
                new MinimumOperationsToMakeArrayValuesEqualToK();

        check("brute force sample", solution.bruteForceMinOperations(
                new int[] {5, 2, 5, 4, 5}, 2), 2);
        check("brute force impossible", solution.bruteForceMinOperations(
                new int[] {2, 1, 2}, 2), -1);

        check("sample", solution.minOperations(new int[] {5, 2, 5, 4, 5}, 2), 2);
        check("already equal", solution.minOperations(new int[] {3, 3, 3}, 3), 0);
        check("impossible below k", solution.minOperations(new int[] {2, 1, 2}, 2), -1);
        check("one distinct value above k", solution.minOperations(new int[] {4, 4, 4}, 1), 1);
        check("multiple distinct values", solution.minOperations(new int[] {9, 7, 5, 3}, 1), 4);
    }
}

/*
 * Brute Force:
 * I copy the array and repeatedly lower the current maximum value to the next
 * smaller distinct value, or directly to k when no smaller value remains.
 *
 * Time Complexity: O(n * m), where n is the number of values and m is the
 * number of distinct values greater than k.
 * Space Complexity: O(n), because the simulation works on a copied array.
 *
 * Optimal Interview Solution:
 * If any value is below k, the target cannot be reached. Otherwise, each
 * distinct value greater than k requires exactly one operation, so I count
 * those distinct values with a set.
 *
 * Time Complexity: O(n), where n is the number of values.
 * Space Complexity: O(n), because the set can store the distinct values above k.
 */
