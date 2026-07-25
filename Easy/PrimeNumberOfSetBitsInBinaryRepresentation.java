class PrimeNumberOfSetBitsInBinaryRepresentation {
    public int bruteForceCountPrimeSetBits(int left, int right) {
        int count = 0;

        for (int number = left; number <= right; number++) {
            int setBits = countSetBitsByString(number);

            if (isPrime(setBits)) {
                count++;
            }
        }

        return count;
    }

    public int countPrimeSetBits(int left, int right) {
        int count = 0;
        int primeBitCounts = (1 << 2) | (1 << 3) | (1 << 5) | (1 << 7) | (1 << 11)
                | (1 << 13) | (1 << 17) | (1 << 19);

        for (int number = left; number <= right; number++) {
            int setBits = Integer.bitCount(number);

            if (((primeBitCounts >> setBits) & 1) == 1) {
                count++;
            }
        }

        return count;
    }

    private int countSetBitsByString(int number) {
        String binary = Integer.toBinaryString(number);
        int count = 0;

        for (int index = 0; index < binary.length(); index++) {
            if (binary.charAt(index) == '1') {
                count++;
            }
        }

        return count;
    }

    private boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            if (number % divisor == 0) {
                return false;
            }
        }

        return true;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        PrimeNumberOfSetBitsInBinaryRepresentation solution = new PrimeNumberOfSetBitsInBinaryRepresentation();

        check("brute force counts prime set bits in a small range",
                solution.bruteForceCountPrimeSetBits(6, 10),
                4);
        check("brute force handles a larger sample range",
                solution.bruteForceCountPrimeSetBits(10, 15),
                5);

        check("counts prime set bits in a small range", solution.countPrimeSetBits(6, 10), 4);
        check("handles a larger sample range", solution.countPrimeSetBits(10, 15), 5);
        check("handles a single number with non-prime set bits", solution.countPrimeSetBits(8, 8), 0);
        check("handles a single number with prime set bits", solution.countPrimeSetBits(14, 14), 1);
    }
}

/*
 * Brute Force:
 * I convert each value in the range to its binary string, count the ones in that
 * string, and test the count for primality by trial division.
 *
 * Time Complexity: O(n * b + n * sqrt(b)), where n is the size of the range and
 * b is the number of bits in the largest value.
 * Space Complexity: O(b), because each binary string stores up to b characters.
 *
 * Optimal Interview Solution:
 * I use the built-in bit count and a bit mask of prime counts, so checking
 * whether the number of set bits is prime is constant time.
 *
 * Time Complexity: O(n), where n is the size of the range.
 * Space Complexity: O(1).
 */
