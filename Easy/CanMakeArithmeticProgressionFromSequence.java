import java.util.Arrays;

class CanMakeArithmeticProgressionFromSequence {
    public boolean bruteForceCanMakeArithmeticProgression(int[] arr) {
        return canArrange(arr, new boolean[arr.length], new int[arr.length], 0);
    }

    private boolean canArrange(int[] arr, boolean[] used, int[] permutation, int index) {
        if (index == arr.length) {
            int difference = permutation[1] - permutation[0];

            for (int i = 2; i < permutation.length; i++) {
                if (permutation[i] - permutation[i - 1] != difference) {
                    return false;
                }
            }

            return true;
        }

        for (int i = 0; i < arr.length; i++) {
            if (used[i]) {
                continue;
            }

            used[i] = true;
            permutation[index] = arr[i];

            if (canArrange(arr, used, permutation, index + 1)) {
                return true;
            }

            used[i] = false;
        }

        return false;
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int difference = arr[1] - arr[0];

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != difference) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    private static int[] values(int... nums) {
        return nums;
    }

    public static void main(String[] args) {
        CanMakeArithmeticProgressionFromSequence solution =
                new CanMakeArithmeticProgressionFromSequence();

        check("brute force accepts reordered progression",
                solution.bruteForceCanMakeArithmeticProgression(values(3, 5, 1)), true);
        check("brute force rejects uneven gaps",
                solution.bruteForceCanMakeArithmeticProgression(values(1, 2, 4)), false);
        check("brute force accepts duplicate progression",
                solution.bruteForceCanMakeArithmeticProgression(values(7, 7, 7)), true);

        check("accepts reordered progression", solution.canMakeArithmeticProgression(values(3, 5, 1)), true);
        check("rejects uneven gaps", solution.canMakeArithmeticProgression(values(1, 2, 4)), false);
        check("accepts negative progression", solution.canMakeArithmeticProgression(values(-1, -5, -3)), true);
        check("accepts duplicate progression", solution.canMakeArithmeticProgression(values(7, 7, 7)), true);
    }
}

/*
 * Brute Force:
 * I try every ordering of the array and check whether any arrangement has the
 * same difference between every adjacent pair.
 *
 * Time Complexity: O(n! * n), because each permutation can require a full gap
 * check.
 * Space Complexity: O(n), because the recursion stores the current permutation
 * and used positions.
 *
 * Optimal Interview Solution:
 * I sort the values and verify that every adjacent pair has the same
 * difference.
 *
 * Time Complexity: O(n log n), because sorting dominates the work.
 * Space Complexity: O(log n), because Java's primitive array sort uses stack
 * space for sorting.
 */
