import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class IntersectionOfTwoArraysII {
    public int[] bruteForceIntersect(int[] nums1, int[] nums2) {
        boolean[] used = new boolean[nums2.length];
        List<Integer> intersection = new ArrayList<>();

        for (int num1 : nums1) {
            for (int index = 0; index < nums2.length; index++) {
                if (!used[index] && num1 == nums2[index]) {
                    used[index] = true;
                    intersection.add(num1);
                    break;
                }
            }
        }

        return toArray(intersection);
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1.length > nums2.length) {
            return intersect(nums2, nums1);
        }

        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums1) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        List<Integer> intersection = new ArrayList<>();
        for (int num : nums2) {
            int count = counts.getOrDefault(num, 0);

            if (count > 0) {
                intersection.add(num);
                counts.put(num, count - 1);
            }
        }

        return toArray(intersection);
    }

    private int[] toArray(List<Integer> values) {
        int[] result = new int[values.size()];

        for (int index = 0; index < values.size(); index++) {
            result[index] = values.get(index);
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
        IntersectionOfTwoArraysII solution = new IntersectionOfTwoArraysII();

        check("brute force keeps duplicate matches", solution.bruteForceIntersect(new int[] {1, 2, 2, 1}, new int[] {2, 2}),
                new int[] {2, 2});

        check("keeps only shared duplicate counts", solution.intersect(new int[] {1, 2, 2, 1}, new int[] {2, 2}),
                new int[] {2, 2});
        check("handles different order", solution.intersect(new int[] {4, 9, 5}, new int[] {9, 4, 9, 8, 4}),
                new int[] {4, 9});
        check("returns empty result", solution.intersect(new int[] {1, 2, 3}, new int[] {4, 5, 6}), new int[] {});
    }
}

/*
 * Brute Force:
 * I scan nums2 for every value in nums1 and mark a nums2 index as used once it
 * has been matched. This preserves the duplicate count in the intersection.
 *
 * Time Complexity: O(m * n), where m and n are the input lengths.
 * Space Complexity: O(n + k), where n is nums2.length and k is the result size.
 *
 * Optimal Interview Solution:
 * I count values from the smaller array, then scan the other array and consume
 * one count whenever a shared value appears.
 *
 * Time Complexity: O(m + n), where m and n are the input lengths.
 * Space Complexity: O(min(m, n) + k), where k is the result size.
 */
