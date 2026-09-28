import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class FindTargetIndicesAfterSortingArray {
    public List<Integer> bruteForceTargetIndices(int[] nums, int target) {
        Arrays.sort(nums);
        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                indices.add(i);
            }
        }

        return indices;
    }

    public List<Integer> targetIndices(int[] nums, int target) {
        int smaller = 0;
        int equal = 0;

        for (int num : nums) {
            if (num < target) {
                smaller++;
            } else if (num == target) {
                equal++;
            }
        }

        List<Integer> indices = new ArrayList<>();

        for (int i = 0; i < equal; i++) {
            indices.add(smaller + i);
        }

        return indices;
    }

    private static void check(String name, List<Integer> actual, List<Integer> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    private static List<Integer> list(int... values) {
        List<Integer> result = new ArrayList<>();

        for (int value : values) {
            result.add(value);
        }

        return result;
    }

    public static void main(String[] args) {
        FindTargetIndicesAfterSortingArray solution = new FindTargetIndicesAfterSortingArray();

        check("brute force sample", solution.bruteForceTargetIndices(nums(1, 2, 5, 2, 3), 2), list(1, 2));
        check("brute force missing", solution.bruteForceTargetIndices(nums(1, 2, 5, 2, 3), 4), list());

        check("sample", solution.targetIndices(nums(1, 2, 5, 2, 3), 2), list(1, 2));
        check("target appears once", solution.targetIndices(nums(1, 2, 5, 2, 3), 3), list(3));
        check("target missing", solution.targetIndices(nums(1, 2, 5, 2, 3), 4), list());
        check("all targets", solution.targetIndices(nums(7, 7, 7), 7), list(0, 1, 2));
    }
}

/*
 * Brute Force:
 * I sort the array and scan the sorted values to collect every index holding
 * the target.
 *
 * Time Complexity: O(n log n), because the array is sorted before scanning it.
 * Space Complexity: O(log n), because Arrays.sort on int arrays uses stack
 * space for sorting.
 *
 * Optimal Interview Solution:
 * I count how many numbers are smaller than target and how many equal target.
 * In the sorted order, the target occupies exactly that consecutive index
 * range.
 *
 * Time Complexity: O(n), because each number is inspected once.
 * Space Complexity: O(1), not counting the returned list of indices.
 */
