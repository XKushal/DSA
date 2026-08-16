class ReplaceElementsWithGreatestElementOnRightSide {
    public int[] bruteForceReplaceElements(int[] arr) {
        int[] replaced = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            int greatest = -1;

            for (int j = i + 1; j < arr.length; j++) {
                greatest = Math.max(greatest, arr[j]);
            }

            replaced[i] = greatest;
        }

        return replaced;
    }

    public int[] replaceElements(int[] arr) {
        int greatest = -1;

        for (int i = arr.length - 1; i >= 0; i--) {
            int current = arr[i];
            arr[i] = greatest;
            greatest = Math.max(greatest, current);
        }

        return arr;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (actual.length != expected.length) {
            throw new AssertionError(name + " length expected " + expected.length + " but got " + actual.length);
        }

        for (int i = 0; i < actual.length; i++) {
            if (actual[i] != expected[i]) {
                throw new AssertionError(name + " expected " + expected[i] + " at index " + i + " but got " + actual[i]);
            }
        }
    }

    private static int[] nums(int... values) {
        return values;
    }

    public static void main(String[] args) {
        ReplaceElementsWithGreatestElementOnRightSide solution = new ReplaceElementsWithGreatestElementOnRightSide();

        check("brute force mixed values", solution.bruteForceReplaceElements(nums(17, 18, 5, 4, 6, 1)),
            nums(18, 6, 6, 6, 1, -1));
        check("brute force single value", solution.bruteForceReplaceElements(nums(400)), nums(-1));

        check("mixed values", solution.replaceElements(nums(17, 18, 5, 4, 6, 1)), nums(18, 6, 6, 6, 1, -1));
        check("single value", solution.replaceElements(nums(400)), nums(-1));
        check("already descending", solution.replaceElements(nums(9, 7, 5, 3)), nums(7, 5, 3, -1));
        check("increasing values", solution.replaceElements(nums(1, 2, 3, 4)), nums(4, 4, 4, -1));
        check("repeated maxima", solution.replaceElements(nums(2, 2, 1, 2)), nums(2, 2, 2, -1));
    }
}

/*
 * Brute Force:
 * I scan every element to the right of each position to find the replacement
 * value, and use -1 for the final element.
 *
 * Time Complexity: O(n^2), because each index may scan the remaining suffix.
 * Space Complexity: O(n), because the replacement values are stored in a new
 * array.
 *
 * Optimal Interview Solution:
 * I walk from right to left while carrying the greatest value seen so far, so
 * each position can be replaced before the current value updates that maximum.
 *
 * Time Complexity: O(n), because the array is scanned once.
 * Space Complexity: O(1), because the replacement is done in place.
 */
