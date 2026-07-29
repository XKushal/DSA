import java.util.HashSet;
import java.util.Set;

class DistributeCandies {
    public int bruteForceDistributeCandies(int[] candyType) {
        int uniqueTypes = 0;

        for (int i = 0; i < candyType.length; i++) {
            boolean alreadyCounted = false;

            for (int j = 0; j < i; j++) {
                if (candyType[i] == candyType[j]) {
                    alreadyCounted = true;
                    break;
                }
            }

            if (!alreadyCounted) {
                uniqueTypes++;
            }
        }

        return Math.min(uniqueTypes, candyType.length / 2);
    }

    public int distributeCandies(int[] candyType) {
        Set<Integer> uniqueTypes = new HashSet<>();

        for (int type : candyType) {
            uniqueTypes.add(type);
        }

        return Math.min(uniqueTypes.size(), candyType.length / 2);
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DistributeCandies solution = new DistributeCandies();

        check("brute force handles repeated pairs", solution.bruteForceDistributeCandies(new int[] {1, 1, 2, 2, 3, 3}), 3);
        check("brute force caps by half", solution.bruteForceDistributeCandies(new int[] {1, 1, 2, 3}), 2);
        check("brute force handles one type", solution.bruteForceDistributeCandies(new int[] {6, 6, 6, 6}), 1);

        check("handles repeated pairs", solution.distributeCandies(new int[] {1, 1, 2, 2, 3, 3}), 3);
        check("caps by half", solution.distributeCandies(new int[] {1, 1, 2, 3}), 2);
        check("handles one type", solution.distributeCandies(new int[] {6, 6, 6, 6}), 1);
        check("handles negative labels", solution.distributeCandies(new int[] {-1, -1, 0, 1, 2, 2}), 3);
    }
}

/*
 * Brute Force:
 * I count each candy type the first time it appears by scanning all earlier
 * candies for a duplicate, then cap the distinct count by the number Alice can
 * receive.
 *
 * Time Complexity: O(n^2), because each candy can scan the earlier portion of
 * the array.
 * Space Complexity: O(1), because only counters and flags are stored.
 *
 * Optimal Interview Solution:
 * I store the distinct candy types in a hash set, then return the smaller value
 * between the number of distinct types and half of the candies.
 *
 * Time Complexity: O(n), because each candy type is inserted into the set once.
 * Space Complexity: O(n), because the set can store every candy type.
 */
