class DuplicateZeros {
    public void bruteForceDuplicateZeros(int[] arr) {
        int[] expanded = new int[arr.length];
        int write = 0;

        for (int value : arr) {
            if (write >= arr.length) {
                break;
            }

            expanded[write++] = value;

            if (value == 0 && write < arr.length) {
                expanded[write++] = 0;
            }
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = expanded[i];
        }
    }

    public void duplicateZeros(int[] arr) {
        int possibleDuplicates = 0;
        int last = arr.length - 1;

        for (int left = 0; left <= last - possibleDuplicates; left++) {
            if (arr[left] == 0) {
                if (left == last - possibleDuplicates) {
                    arr[last] = 0;
                    last--;
                    break;
                }

                possibleDuplicates++;
            }
        }

        int read = last - possibleDuplicates;
        int write = last;

        while (read >= 0) {
            if (arr[read] == 0) {
                arr[write--] = 0;
                arr[write--] = 0;
            } else {
                arr[write--] = arr[read];
            }

            read--;
        }
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
        DuplicateZeros solution = new DuplicateZeros();

        int[] bruteMixed = nums(1, 0, 2, 3, 0, 4, 5, 0);
        solution.bruteForceDuplicateZeros(bruteMixed);
        check("brute force mixed zeros", bruteMixed, nums(1, 0, 0, 2, 3, 0, 0, 4));

        int[] bruteNoZeros = nums(1, 2, 3);
        solution.bruteForceDuplicateZeros(bruteNoZeros);
        check("brute force no zeros", bruteNoZeros, nums(1, 2, 3));

        int[] mixed = nums(1, 0, 2, 3, 0, 4, 5, 0);
        solution.duplicateZeros(mixed);
        check("mixed zeros", mixed, nums(1, 0, 0, 2, 3, 0, 0, 4));

        int[] noZeros = nums(1, 2, 3);
        solution.duplicateZeros(noZeros);
        check("no zeros", noZeros, nums(1, 2, 3));

        int[] trailingBoundary = nums(8, 4, 5, 0, 0, 0, 0, 7);
        solution.duplicateZeros(trailingBoundary);
        check("trailing boundary zeros", trailingBoundary, nums(8, 4, 5, 0, 0, 0, 0, 0));

        int[] singleZero = nums(0);
        solution.duplicateZeros(singleZero);
        check("single zero", singleZero, nums(0));

        int[] clippedZero = nums(1, 2, 0);
        solution.duplicateZeros(clippedZero);
        check("clipped final zero", clippedZero, nums(1, 2, 0));
    }
}

/*
 * Brute Force:
 * I build the expanded view in a second array, writing an extra zero whenever
 * the current value is zero, and then copy the bounded result back.
 *
 * Time Complexity: O(n), because the input and copied result are each scanned
 * at most once.
 * Space Complexity: O(n), because the expanded view uses another array.
 *
 * Optimal Interview Solution:
 * I first count how many zeros can be duplicated inside the fixed-size array,
 * then write values backward so unread values are not overwritten.
 *
 * Time Complexity: O(n), because both passes move through the array linearly.
 * Space Complexity: O(1), because the transformation is done in place.
 */
