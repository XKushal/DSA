class ValidMountainArray {
    public boolean bruteForceValidMountainArray(int[] arr) {
        for (int peak = 1; peak < arr.length - 1; peak++) {
            boolean strictlyIncreasing = true;
            for (int index = 1; index <= peak; index++) {
                if (arr[index] <= arr[index - 1]) {
                    strictlyIncreasing = false;
                    break;
                }
            }

            if (!strictlyIncreasing) {
                continue;
            }

            boolean strictlyDecreasing = true;
            for (int index = peak + 1; index < arr.length; index++) {
                if (arr[index] >= arr[index - 1]) {
                    strictlyDecreasing = false;
                    break;
                }
            }

            if (strictlyDecreasing) {
                return true;
            }
        }

        return false;
    }

    public boolean validMountainArray(int[] arr) {
        if (arr.length < 3) {
            return false;
        }

        int index = 0;

        while (index + 1 < arr.length && arr[index] < arr[index + 1]) {
            index++;
        }

        if (index == 0 || index == arr.length - 1) {
            return false;
        }

        while (index + 1 < arr.length && arr[index] > arr[index + 1]) {
            index++;
        }

        return index == arr.length - 1;
    }

    private static void check(String name, boolean actual, boolean expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        ValidMountainArray solution = new ValidMountainArray();

        check("brute force accepts a valid mountain",
                solution.bruteForceValidMountainArray(new int[] {0, 3, 2, 1}), true);

        check("accepts a valid mountain", solution.validMountainArray(new int[] {0, 2, 3, 4, 5, 2, 1, 0}), true);
        check("rejects an array without a descent", solution.validMountainArray(new int[] {0, 1, 2, 3}), false);
        check("rejects an array without an ascent", solution.validMountainArray(new int[] {3, 2, 1}), false);
        check("rejects equal adjacent values", solution.validMountainArray(new int[] {0, 2, 2, 1}), false);
        check("rejects arrays shorter than three", solution.validMountainArray(new int[] {1, 2}), false);
    }
}

/*
 * Brute Force:
 * I try every internal index as the peak. For each candidate, I separately
 * verify that values strictly increase before it and strictly decrease after.
 *
 * Time Complexity: O(n^2), where n is the array length.
 * Space Complexity: O(1), because only indices and booleans are stored.
 *
 * Optimal Interview Solution:
 * I walk uphill while values strictly increase, require the peak to be inside
 * the array, then walk downhill while values strictly decrease. A valid
 * mountain consumes the entire array.
 *
 * Time Complexity: O(n), where n is the array length.
 * Space Complexity: O(1), because only one index is stored.
 */
