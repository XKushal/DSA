import java.util.HashMap;
import java.util.Map;

class ContainsDuplicateII {
    public boolean bruteForceContainsNearbyDuplicate(int[] nums, int k) {
        for (int left = 0; left < nums.length; left++) {
            for (int right = left + 1; right < nums.length && right <= left + k; right++) {
                if (nums[left] == nums[right]) {
                    return true;
                }
            }
        }

        return false;
    }

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> lastSeenIndex = new HashMap<>();

        for (int index = 0; index < nums.length; index++) {
            int num = nums[index];

            if (lastSeenIndex.containsKey(num) && index - lastSeenIndex.get(num) <= k) {
                return true;
            }

            lastSeenIndex.put(num, index);
        }

        return false;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ContainsDuplicateII solution = new ContainsDuplicateII();

        check("brute force finds nearby duplicate", solution.bruteForceContainsNearbyDuplicate(new int[] {1, 2, 3, 1}, 3), true);
        check("brute force rejects far duplicate", solution.bruteForceContainsNearbyDuplicate(new int[] {1, 0, 1, 1}, 0), false);

        check("finds nearby duplicate", solution.containsNearbyDuplicate(new int[] {1, 2, 3, 1}, 3), true);
        check("rejects duplicate outside window", solution.containsNearbyDuplicate(new int[] {1, 2, 3, 1, 2, 3}, 2), false);
        check("finds adjacent duplicate", solution.containsNearbyDuplicate(new int[] {1, 0, 1, 1}, 1), true);
    }
}

/*
 * Brute Force:
 * I compare each number with the numbers up to k positions after it. If any
 * matching pair is inside that window, the array has a nearby duplicate.
 *
 * Time Complexity: O(n * min(n, k)), where n is the length of nums.
 * Space Complexity: O(1), because no extra data structure is used.
 *
 * Optimal Interview Solution:
 * I scan the array once while storing the most recent index for each value. A
 * repeated value is valid only when the distance from its previous index is at
 * most k.
 *
 * Time Complexity: O(n), where n is the length of nums.
 * Space Complexity: O(n), because the map can store each distinct value.
 */
