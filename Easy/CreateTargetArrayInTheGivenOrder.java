import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class CreateTargetArrayInTheGivenOrder {
    public int[] bruteForceCreateTargetArray(int[] nums, int[] index) {
        int n = nums.length;
        int[] target = new int[n];
        int size = 0;

        for (int i = 0; i < n; i++) {
            for (int j = size; j > index[i]; j--) {
                target[j] = target[j - 1];
            }

            target[index[i]] = nums[i];
            size++;
        }

        return target;
    }

    public int[] createTargetArray(int[] nums, int[] index) {
        List<Integer> target = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            target.add(index[i], nums[i]);
        }

        int[] result = new int[target.size()];
        for (int i = 0; i < target.size(); i++) {
            result[i] = target.get(i);
        }

        return result;
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
        CreateTargetArrayInTheGivenOrder solution = new CreateTargetArrayInTheGivenOrder();

        check("brute force front insertions",
            solution.bruteForceCreateTargetArray(nums(0, 1, 2, 3, 4), nums(0, 1, 2, 2, 1)),
            nums(0, 4, 1, 3, 2));
        check("brute force all front insertions",
            solution.bruteForceCreateTargetArray(nums(1, 2, 3, 4), nums(0, 0, 0, 0)),
            nums(4, 3, 2, 1));

        check("front insertions", solution.createTargetArray(nums(0, 1, 2, 3, 4), nums(0, 1, 2, 2, 1)),
            nums(0, 4, 1, 3, 2));
        check("all front insertions", solution.createTargetArray(nums(1, 2, 3, 4), nums(0, 0, 0, 0)),
            nums(4, 3, 2, 1));
        check("append each value", solution.createTargetArray(nums(1, 2, 3), nums(0, 1, 2)), nums(1, 2, 3));
        check("single value", solution.createTargetArray(nums(7), nums(0)), nums(7));
    }
}

/*
 * Brute Force:
 * I keep a fixed-size target array and manually shift every element at and
 * after the insertion point before writing the new value.
 *
 * Time Complexity: O(n^2), because each insertion can shift the current
 * contents of the target array.
 * Space Complexity: O(n), because the target array stores the result.
 *
 * Optimal Interview Solution:
 * I use a dynamic list and insert each value at its requested index, then copy
 * the list into the required array result.
 *
 * Time Complexity: O(n^2), because indexed insertion in an array-backed list
 * can shift existing values.
 * Space Complexity: O(n), because the list and result store the target values.
 */
