import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class NextGreaterElementI {
    public int[] bruteForceNextGreaterElement(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length];

        for (int index = 0; index < nums1.length; index++) {
            int nums2Index = findIndex(nums2, nums1[index]);
            result[index] = findNextGreater(nums2, nums2Index);
        }

        return result;
    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nextGreaterByValue = new HashMap<>();
        ArrayDeque<Integer> decreasingValues = new ArrayDeque<>();

        for (int value : nums2) {
            while (!decreasingValues.isEmpty() && decreasingValues.peek() < value) {
                nextGreaterByValue.put(decreasingValues.pop(), value);
            }

            decreasingValues.push(value);
        }

        int[] result = new int[nums1.length];
        for (int index = 0; index < nums1.length; index++) {
            result[index] = nextGreaterByValue.getOrDefault(nums1[index], -1);
        }

        return result;
    }

    private int findIndex(int[] nums, int target) {
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] == target) {
                return index;
            }
        }

        return -1;
    }

    private int findNextGreater(int[] nums, int startIndex) {
        for (int index = startIndex + 1; index < nums.length; index++) {
            if (nums[index] > nums[startIndex]) {
                return nums[index];
            }
        }

        return -1;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        NextGreaterElementI solution = new NextGreaterElementI();

        check("brute force handles mixed next greater values",
                solution.bruteForceNextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2}),
                new int[] {-1, 3, -1});
        check("brute force handles increasing suffix",
                solution.bruteForceNextGreaterElement(new int[] {2, 4}, new int[] {1, 2, 3, 4}),
                new int[] {3, -1});

        check("handles mixed next greater values", solution.nextGreaterElement(new int[] {4, 1, 2}, new int[] {1, 3, 4, 2}),
                new int[] {-1, 3, -1});
        check("handles increasing suffix", solution.nextGreaterElement(new int[] {2, 4}, new int[] {1, 2, 3, 4}),
                new int[] {3, -1});
        check("handles descending values", solution.nextGreaterElement(new int[] {3, 2, 1}, new int[] {3, 2, 1}),
                new int[] {-1, -1, -1});
        check("handles single query value", solution.nextGreaterElement(new int[] {1}, new int[] {1, 5, 3}),
                new int[] {5});
    }
}

/*
 * Brute Force:
 * I find each nums1 value inside nums2, then scan to the right until I find the
 * first greater value.
 *
 * Time Complexity: O(m * n), where m is nums1.length and n is nums2.length.
 * Space Complexity: O(m), because the result array stores one answer per query.
 *
 * Optimal Interview Solution:
 * I scan nums2 once with a decreasing stack. When a larger value appears, it is
 * the next greater value for every smaller stack top it removes.
 *
 * Time Complexity: O(m + n), where m is nums1.length and n is nums2.length.
 * Space Complexity: O(n + m), because the map and stack store values from nums2
 * and the result stores one answer per nums1 value.
 */
