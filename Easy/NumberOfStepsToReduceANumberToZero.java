class NumberOfStepsToReduceANumberToZero {
    public int bruteForceNumberOfSteps(int num) {
        int steps = 0;

        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
            } else {
                num--;
            }

            steps++;
        }

        return steps;
    }

    public int numberOfSteps(int num) {
        if (num == 0) {
            return 0;
        }

        return Integer.SIZE - Integer.numberOfLeadingZeros(num) - 1 + Integer.bitCount(num);
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        NumberOfStepsToReduceANumberToZero solution = new NumberOfStepsToReduceANumberToZero();

        check("brute force mixed operations", solution.bruteForceNumberOfSteps(14), 6);
        check("brute force zero", solution.bruteForceNumberOfSteps(0), 0);

        check("mixed operations", solution.numberOfSteps(14), 6);
        check("power of two", solution.numberOfSteps(8), 4);
        check("one", solution.numberOfSteps(1), 1);
        check("zero", solution.numberOfSteps(0), 0);
        check("larger value", solution.numberOfSteps(123), 12);
    }
}

/*
 * Brute Force:
 * I simulate the process exactly: divide even numbers by two, subtract one from
 * odd numbers, and count every operation until the value reaches zero.
 *
 * Time Complexity: O(log n), because each one or two operations removes one
 * binary digit from the number.
 * Space Complexity: O(1), because only the current value and count are stored.
 *
 * Optimal Interview Solution:
 * I count the required binary operations directly. Every bit after the leading
 * bit needs one division, and every set bit needs one subtraction.
 *
 * Time Complexity: O(1), because Java checks the fixed-width integer bits.
 * Space Complexity: O(1), because no extra data structure is used.
 */
