class AddDigits {
    public int bruteForceAddDigits(int num) {
        while (num >= 10) {
            int digitSum = 0;

            while (num > 0) {
                digitSum += num % 10;
                num /= 10;
            }

            num = digitSum;
        }

        return num;
    }

    public int addDigits(int num) {
        if (num == 0) {
            return 0;
        }

        return 1 + (num - 1) % 9;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        AddDigits solution = new AddDigits();

        check("brute force reduces repeated digits", solution.bruteForceAddDigits(38), 2);
        check("brute force handles zero", solution.bruteForceAddDigits(0), 0);
        check("brute force handles one digit", solution.bruteForceAddDigits(7), 7);

        check("reduces repeated digits", solution.addDigits(38), 2);
        check("handles zero", solution.addDigits(0), 0);
        check("handles one digit", solution.addDigits(7), 7);
        check("handles multiple reductions", solution.addDigits(9999), 9);
    }
}

/*
 * Brute Force:
 * I repeatedly sum the digits until the number has only one digit left.
 *
 * Time Complexity: O(log n), because each pass scans the digits and the value
 * quickly shrinks.
 * Space Complexity: O(1), because only numeric counters are stored.
 *
 * Optimal Interview Solution:
 * I use the digital root pattern. Every positive number maps to its remainder
 * modulo 9, except multiples of 9 map to 9.
 *
 * Time Complexity: O(1), because the answer is calculated directly.
 * Space Complexity: O(1), because no extra storage grows with the input.
 */
