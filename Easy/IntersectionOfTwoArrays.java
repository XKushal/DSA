import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class IntersectionOfTwoArrays {
    public int[] bruteForceIntersection(int[] nums1, int[] nums2) {
        List<Integer> intersection = new ArrayList<>();

        for (int num1 : nums1) {
            for (int num2 : nums2) {
                if (num1 == num2 && !intersection.contains(num1)) {
                    intersection.add(num1);
                    break;
                }
            }
        }

        return toArray(intersection);
    }

    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> values = new HashSet<>();
        for (int number : nums1) {
            values.add(number);
        }

        Set<Integer> intersection = new HashSet<>();
        for (int number : nums2) {
            if (values.contains(number)) {
                intersection.add(number);
            }
        }

        return toArray(intersection);
    }

    private int[] toArray(Iterable<Integer> values) {
        List<Integer> list = new ArrayList<>();
        for (int value : values) {
            list.add(value);
        }

        int[] result = new int[list.size()];
        for (int index = 0; index < list.size(); index++) {
            result[index] = list.get(index);
        }

        return result;
    }

    private static void check(String name, int[] actual, int[] expected) {
        Arrays.sort(actual);
        Arrays.sort(expected);

        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        IntersectionOfTwoArrays solution = new IntersectionOfTwoArrays();

        check("brute force returns unique shared values",
                solution.bruteForceIntersection(new int[] {1, 2, 2, 1}, new int[] {2, 2}), new int[] {2});

        check("returns unique shared values", solution.intersection(new int[] {1, 2, 2, 1}, new int[] {2, 2}),
                new int[] {2});
        check("handles different order", solution.intersection(new int[] {4, 9, 5}, new int[] {9, 4, 9, 8, 4}),
                new int[] {4, 9});
        check("returns empty result", solution.intersection(new int[] {1, 2, 3}, new int[] {4, 5, 6}),
                new int[] {});
        check("removes duplicates from both inputs",
                solution.intersection(new int[] {1, 1, 1}, new int[] {1, 1}), new int[] {1});
    }
}

/*
 * Brute Force:
 * I compare every value in nums1 with every value in nums2 and add a match only
 * when it is not already in the result.
 *
 * Time Complexity: O(m * n), where m and n are the input lengths.
 * Space Complexity: O(k), where k is the number of unique shared values.
 *
 * Optimal Interview Solution:
 * I store the unique values from nums1 in a hash set, scan nums2 for matches,
 * and use a second set to keep the result unique.
 *
 * Time Complexity: O(m + n) on average, where m and n are the input lengths.
 * Space Complexity: O(m + k), where k is the number of unique shared values.
 */
