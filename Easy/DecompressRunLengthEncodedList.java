import java.util.Arrays;

class DecompressRunLengthEncodedList {
    public int[] bruteForceDecompressRLElist(int[] nums) {
        int[] result = new int[0];

        for (int i = 0; i < nums.length; i += 2) {
            int frequency = nums[i];
            int value = nums[i + 1];
            int oldLength = result.length;

            result = Arrays.copyOf(result, oldLength + frequency);

            for (int j = oldLength; j < result.length; j++) {
                result[j] = value;
            }
        }

        return result;
    }

    public int[] decompressRLElist(int[] nums) {
        int totalLength = 0;

        for (int i = 0; i < nums.length; i += 2) {
            totalLength += nums[i];
        }

        int[] result = new int[totalLength];
        int index = 0;

        for (int i = 0; i < nums.length; i += 2) {
            int frequency = nums[i];
            int value = nums[i + 1];

            for (int j = 0; j < frequency; j++) {
                result[index] = value;
                index++;
            }
        }

        return result;
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(name + " expected " + Arrays.toString(expected)
                    + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        DecompressRunLengthEncodedList solution = new DecompressRunLengthEncodedList();

        check("brute force sample one", solution.bruteForceDecompressRLElist(new int[] {1, 2, 3, 4}),
                new int[] {2, 4, 4, 4});
        check("brute force sample two", solution.bruteForceDecompressRLElist(new int[] {1, 1, 2, 3}),
                new int[] {1, 3, 3});

        check("sample one", solution.decompressRLElist(new int[] {1, 2, 3, 4}), new int[] {2, 4, 4, 4});
        check("sample two", solution.decompressRLElist(new int[] {1, 1, 2, 3}), new int[] {1, 3, 3});
        check("single pair", solution.decompressRLElist(new int[] {3, 5}), new int[] {5, 5, 5});
        check("mixed frequencies", solution.decompressRLElist(new int[] {2, 7, 1, 8, 4, 9}),
                new int[] {7, 7, 8, 9, 9, 9, 9});
    }
}

/*
 * Brute Force:
 * I expand each frequency-value pair by growing the result array and appending
 * the repeated values for that pair.
 *
 * Time Complexity: O(m^2), where m is the decompressed length, because repeated
 * array copies can recopy values already produced.
 * Space Complexity: O(m), because the decompressed result is stored.
 *
 * Optimal Interview Solution:
 * I first compute the decompressed length, allocate the exact result size, and
 * then fill each run directly into the output array.
 *
 * Time Complexity: O(n + m), where n is the encoded array length and m is the
 * decompressed length.
 * Space Complexity: O(m), because the decompressed result is returned.
 */
