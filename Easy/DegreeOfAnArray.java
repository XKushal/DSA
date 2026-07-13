import java.util.HashMap;
import java.util.Map;

class DegreeOfAnArray {
    public int bruteForceFindShortestSubArray(int[] nums) {
        int targetDegree = findDegree(nums);
        int shortest = nums.length;

        for (int start = 0; start < nums.length; start++) {
            Map<Integer, Integer> counts = new HashMap<>();
            int windowDegree = 0;

            for (int end = start; end < nums.length; end++) {
                int count = counts.getOrDefault(nums[end], 0) + 1;
                counts.put(nums[end], count);
                windowDegree = Math.max(windowDegree, count);

                if (windowDegree == targetDegree) {
                    shortest = Math.min(shortest, end - start + 1);
                    break;
                }
            }
        }

        return shortest;
    }

    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> firstIndex = new HashMap<>();
        Map<Integer, Integer> counts = new HashMap<>();
        int degree = 0;
        int shortest = nums.length;

        for (int index = 0; index < nums.length; index++) {
            int value = nums[index];
            firstIndex.putIfAbsent(value, index);

            int count = counts.getOrDefault(value, 0) + 1;
            counts.put(value, count);

            int length = index - firstIndex.get(value) + 1;
            if (count > degree) {
                degree = count;
                shortest = length;
            } else if (count == degree) {
                shortest = Math.min(shortest, length);
            }
        }

        return shortest;
    }

    private int findDegree(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        int degree = 0;

        for (int num : nums) {
            int count = counts.getOrDefault(num, 0) + 1;
            counts.put(num, count);
            degree = Math.max(degree, count);
        }

        return degree;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DegreeOfAnArray solution = new DegreeOfAnArray();

        check("brute force finds shortest degree subarray",
                solution.bruteForceFindShortestSubArray(new int[] {1, 2, 2, 3, 1}), 2);
        check("brute force handles later degree winner",
                solution.bruteForceFindShortestSubArray(new int[] {1, 2, 2, 3, 1, 4, 2}), 6);

        check("finds compact repeated value", solution.findShortestSubArray(new int[] {1, 2, 2, 3, 1}), 2);
        check("finds shortest subarray with same degree",
                solution.findShortestSubArray(new int[] {1, 2, 2, 3, 1, 4, 2}), 6);
        check("chooses shorter tied degree",
                solution.findShortestSubArray(new int[] {1, 2, 2, 3, 3, 1}), 2);
        check("handles one value", solution.findShortestSubArray(new int[] {7}), 1);
        check("handles all equal values", solution.findShortestSubArray(new int[] {4, 4, 4}), 3);
    }
}

/*
 * Brute Force:
 * I first compute the array's degree, then expand every possible starting
 * position until the current subarray reaches that same degree. Each matching
 * subarray is a candidate answer.
 *
 * Time Complexity: O(n^2), where n is the number of values, because every
 * starting position can scan through the rest of the array.
 * Space Complexity: O(n), because the counting map can hold every distinct
 * value in the current subarray.
 *
 * Optimal Interview Solution:
 * I scan once while tracking each value's first index and count. Whenever a
 * value reaches a new highest frequency, or ties the current degree, I update
 * the shortest span covering that value's full frequency.
 *
 * Time Complexity: O(n), because each value is processed once.
 * Space Complexity: O(n), because the maps store first indices and counts for
 * distinct values.
 */
