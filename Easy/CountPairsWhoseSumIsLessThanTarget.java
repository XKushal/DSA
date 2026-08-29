import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class CountPairsWhoseSumIsLessThanTarget {
    public int bruteForceCountPairs(List<Integer> nums, int target) {
        int pairs = 0;

        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    pairs++;
                }
            }
        }

        return pairs;
    }

    public int countPairs(List<Integer> nums, int target) {
        List<Integer> sorted = new ArrayList<>(nums);
        Collections.sort(sorted);

        int pairs = 0;
        int left = 0;
        int right = sorted.size() - 1;

        while (left < right) {
            if (sorted.get(left) + sorted.get(right) < target) {
                pairs += right - left;
                left++;
            } else {
                right--;
            }
        }

        return pairs;
    }

    private static List<Integer> nums(Integer... values) {
        return Arrays.asList(values);
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountPairsWhoseSumIsLessThanTarget solution = new CountPairsWhoseSumIsLessThanTarget();

        check("brute force sample", solution.bruteForceCountPairs(nums(-1, 1, 2, 3, 1), 2), 3);
        check("brute force negative target", solution.bruteForceCountPairs(nums(-6, 2, 5, -2, -7, -1, 3), -2), 10);

        check("sample", solution.countPairs(nums(-1, 1, 2, 3, 1), 2), 3);
        check("negative target", solution.countPairs(nums(-6, 2, 5, -2, -7, -1, 3), -2), 10);
        check("no pairs", solution.countPairs(nums(5, 6, 7), 4), 0);
        check("all pairs", solution.countPairs(nums(1, 1, 1, 1), 3), 6);
    }
}

/*
 * Brute Force:
 * I try every index pair and count the pair when its sum is less than the
 * target.
 *
 * Time Complexity: O(n^2), because each pair of numbers can be checked once.
 * Space Complexity: O(1), because only the pair counter is stored.
 *
 * Optimal Interview Solution:
 * I sort a copy of the values and use two pointers. When the smallest current
 * number plus the largest current number is below target, that smallest number
 * also forms valid pairs with every number before the right pointer.
 *
 * Time Complexity: O(n log n), because sorting dominates the linear pointer
 * scan.
 * Space Complexity: O(n), because the input values are copied before sorting.
 */
