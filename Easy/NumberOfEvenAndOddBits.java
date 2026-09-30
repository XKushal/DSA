import java.util.Arrays;

class NumberOfEvenAndOddBits {
    public int[] bruteForceEvenOddBit(int n) {
        int even = 0;
        int odd = 0;
        String bits = Integer.toBinaryString(n);

        for (int i = 0; i < bits.length(); i++) {
            if (bits.charAt(i) == '1') {
                int bitIndex = bits.length() - 1 - i;

                if (bitIndex % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }
        }

        return new int[] {even, odd};
    }

    public int[] evenOddBit(int n) {
        int even = 0;
        int odd = 0;
        int bitIndex = 0;

        while (n > 0) {
            if ((n & 1) == 1) {
                if (bitIndex % 2 == 0) {
                    even++;
                } else {
                    odd++;
                }
            }

            bitIndex++;
            n >>= 1;
        }

        return new int[] {even, odd};
    }

    private static void check(String name, int[] actual, int[] expected) {
        if (!Arrays.equals(actual, expected)) {
            throw new AssertionError(
                    name + " expected " + Arrays.toString(expected) + " but got " + Arrays.toString(actual));
        }
    }

    public static void main(String[] args) {
        NumberOfEvenAndOddBits solution = new NumberOfEvenAndOddBits();

        check("brute force sample one", solution.bruteForceEvenOddBit(50), new int[] {1, 2});
        check("brute force sample two", solution.bruteForceEvenOddBit(2), new int[] {0, 1});

        check("sample one", solution.evenOddBit(50), new int[] {1, 2});
        check("sample two", solution.evenOddBit(2), new int[] {0, 1});
        check("only zero index bit", solution.evenOddBit(1), new int[] {1, 0});
        check("several even bits", solution.evenOddBit(85), new int[] {4, 0});
        check("several odd bits", solution.evenOddBit(42), new int[] {0, 3});
    }
}

/*
 * Brute Force:
 * I convert the number to binary text, then count every one bit according to
 * its index from the right side of the string.
 *
 * Time Complexity: O(b), where b is the number of binary digits.
 * Space Complexity: O(b), because the binary string stores every bit.
 *
 * Optimal Interview Solution:
 * I inspect the least significant bit directly, shift the number right, and
 * alternate between even and odd bit positions.
 *
 * Time Complexity: O(b), because every binary digit is inspected once.
 * Space Complexity: O(1), because only counters and the current bit index are
 * stored.
 */
