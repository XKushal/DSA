import java.util.HashSet;
import java.util.Set;

class ContainsDuplicate {
    public boolean bruteForceContainsDuplicate(int[] nums) {
        for (int left = 0; left < nums.length; left++) {
            for (int right = left + 1; right < nums.length; right++) {
                if (nums[left] == nums[right]) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> seen = new HashSet<>();

        for (int num : nums) {
            if (seen.contains(num)) {
                return true;
            }
            seen.add(num);
        }

        return false;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ContainsDuplicate solution = new ContainsDuplicate();

        check("brute force finds repeated value", solution.bruteForceContainsDuplicate(new int[] {1, 2, 3, 1}), true);
        check("brute force rejects all unique values", solution.bruteForceContainsDuplicate(new int[] {1, 2, 3, 4}), false);

        check("finds repeated value", solution.containsDuplicate(new int[] {1, 2, 3, 1}), true);
        check("rejects all unique values", solution.containsDuplicate(new int[] {1, 2, 3, 4}), false);
        check("finds duplicate in mixed values", solution.containsDuplicate(new int[] {1, 1, 1, 3, 3, 4, 3, 2, 4, 2}), true);
    }
}

/*
 * Brute Force:
 * I compare every number with each number after it. If any pair has the same
 * value, the array contains a duplicate.
 *
 * Time Complexity: O(n^2), where n is the length of nums.
 * Space Complexity: O(1), because no extra data structure is used.
 *
 * Optimal Interview Solution:
 * I scan the array once while storing values in a HashSet. When a value is
 * already in the set, I can return true immediately.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(n), because the set can store every value once.
 */
