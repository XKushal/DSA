import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class FindAllNumbersDisappearedInAnArray {
    public List<Integer> bruteForceFindDisappearedNumbers(int[] nums) {
        Set<Integer> present = new HashSet<>();

        for (int num : nums) {
            present.add(num);
        }

        List<Integer> missing = new ArrayList<>();

        for (int value = 1; value <= nums.length; value++) {
            if (!present.contains(value)) {
                missing.add(value);
            }
        }

        return missing;
    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int num : nums) {
            int index = Math.abs(num) - 1;

            if (nums[index] > 0) {
                nums[index] = -nums[index];
            }
        }

        List<Integer> missing = new ArrayList<>();

        for (int index = 0; index < nums.length; index++) {
            if (nums[index] > 0) {
                missing.add(index + 1);
            }
        }

        return missing;
    }

    private static void check(String name, List<Integer> actual, List<Integer> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        FindAllNumbersDisappearedInAnArray solution = new FindAllNumbersDisappearedInAnArray();

        check("brute force finds missing middle values",
                solution.bruteForceFindDisappearedNumbers(new int[] {4, 3, 2, 7, 8, 2, 3, 1}),
                Arrays.asList(5, 6));
        check("brute force handles no missing values",
                solution.bruteForceFindDisappearedNumbers(new int[] {1, 2, 3}),
                new ArrayList<>());

        check("finds missing middle values",
                solution.findDisappearedNumbers(new int[] {4, 3, 2, 7, 8, 2, 3, 1}),
                Arrays.asList(5, 6));
        check("finds one missing value",
                solution.findDisappearedNumbers(new int[] {1, 1}),
                Arrays.asList(2));
        check("handles no missing values",
                solution.findDisappearedNumbers(new int[] {1, 2, 3}),
                new ArrayList<>());
        check("handles every value duplicated except tail",
                solution.findDisappearedNumbers(new int[] {2, 2, 2, 2}),
                Arrays.asList(1, 3, 4));
    }
}

/*
 * Brute Force:
 * I store every number in a set, then scan the full range from 1 to n and add
 * each value that never appeared in the input.
 *
 * Time Complexity: O(n), where n is the array length, because the array and
 * the value range are each scanned once.
 * Space Complexity: O(n), because the set can store every distinct value.
 *
 * Optimal Interview Solution:
 * I use each number as an index marker by flipping the value at that index to
 * negative. Any position that stays positive represents a missing value.
 *
 * Time Complexity: O(n), because each array position is visited a constant
 * number of times.
 * Space Complexity: O(1) auxiliary space, because the input array carries the
 * markers and the result list is the required output.
 */
