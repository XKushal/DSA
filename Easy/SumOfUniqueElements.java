import java.util.HashMap;
import java.util.Map;

class SumOfUniqueElements {
    public int bruteForceSumOfUnique(int[] nums) {
        int sum = 0;

        for (int i = 0; i < nums.length; i++) {
            int count = 0;

            for (int j = 0; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }

            if (count == 1) {
                sum += nums[i];
            }
        }

        return sum;
    }

    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();

        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }

        int sum = 0;

        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (entry.getValue() == 1) {
                sum += entry.getKey();
            }
        }

        return sum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SumOfUniqueElements solution = new SumOfUniqueElements();

        check("brute force sums distinct values", solution.bruteForceSumOfUnique(new int[] {1, 2, 3, 2}), 4);
        check("brute force handles no unique values", solution.bruteForceSumOfUnique(new int[] {1, 1, 2, 2}), 0);
        check("brute force handles all unique values", solution.bruteForceSumOfUnique(new int[] {5, 6, 7}), 18);

        check("sums distinct values", solution.sumOfUnique(new int[] {1, 2, 3, 2}), 4);
        check("handles multiple duplicates", solution.sumOfUnique(new int[] {1, 1, 1, 2, 3}), 5);
        check("handles no unique values", solution.sumOfUnique(new int[] {1, 1, 2, 2}), 0);
        check("handles all unique values", solution.sumOfUnique(new int[] {5, 6, 7}), 18);
    }
}

/*
 * Brute Force:
 * I count how many times each value appears by comparing it with every other
 * value, then add it only when it appears exactly once.
 *
 * Time Complexity: O(n^2), because each value can be compared with every other
 * value.
 * Space Complexity: O(1), because only counters and the running sum are stored.
 *
 * Optimal Interview Solution:
 * I count each value with a hash map, then add only the values whose frequency
 * is one.
 *
 * Time Complexity: O(n), because the array and frequency entries are each
 * scanned once.
 * Space Complexity: O(n), because the map can store every distinct value.
 */
