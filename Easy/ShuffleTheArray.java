import java.util.Arrays;

class ShuffleTheArray {
    public int[] bruteForceShuffle(int[] nums, int n) {
        int[] firstHalf = new int[n];
        int[] secondHalf = new int[n];

        for (int i = 0; i < n; i++) {
            firstHalf[i] = nums[i];
            secondHalf[i] = nums[i + n];
        }

        int[] shuffled = new int[nums.length];
        int index = 0;
        for (int i = 0; i < n; i++) {
            shuffled[index++] = firstHalf[i];
            shuffled[index++] = secondHalf[i];
        }

        return shuffled;
    }

    public int[] shuffle(int[] nums, int n) {
        int[] shuffled = new int[nums.length];

        for (int i = 0; i < n; i++) {
            shuffled[2 * i] = nums[i];
            shuffled[2 * i + 1] = nums[i + n];
        }

        return shuffled;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                + " but got " + Arrays.toString(actual));
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    public static void main(String[] args) {
        ShuffleTheArray solution = new ShuffleTheArray();

        check("brute force sample", solution.bruteForceShuffle(nums(2, 5, 1, 3, 4, 7), 3),
            nums(2, 3, 5, 4, 1, 7));
        check("brute force two pairs", solution.bruteForceShuffle(nums(1, 2, 3, 4), 2),
            nums(1, 3, 2, 4));

        check("sample", solution.shuffle(nums(2, 5, 1, 3, 4, 7), 3), nums(2, 3, 5, 4, 1, 7));
        check("two pairs", solution.shuffle(nums(1, 2, 3, 4), 2), nums(1, 3, 2, 4));
        check("single pair", solution.shuffle(nums(9, 8), 1), nums(9, 8));
        check("repeated values", solution.shuffle(nums(4, 4, 4, 1, 1, 1), 3), nums(4, 1, 4, 1, 4, 1));
    }
}

/*
 * Brute Force:
 * I copy the first and second halves into separate arrays, then rebuild the
 * answer by alternating values from those halves.
 *
 * Time Complexity: O(n), because each input value is copied and written once.
 * Space Complexity: O(n), because two temporary half arrays and the answer are
 * stored.
 *
 * Optimal Interview Solution:
 * I write each pair directly into its final even and odd positions in the
 * returned array.
 *
 * Time Complexity: O(n), because each pair is processed once.
 * Space Complexity: O(n), because the returned shuffled array is required.
 */
