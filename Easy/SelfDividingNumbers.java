import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SelfDividingNumbers {
    public List<Integer> bruteForceSelfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();

        for (int number = left; number <= right; number++) {
            if (isSelfDividingByString(number)) {
                result.add(number);
            }
        }

        return result;
    }

    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> result = new ArrayList<>();

        for (int number = left; number <= right; number++) {
            if (isSelfDividing(number)) {
                result.add(number);
            }
        }

        return result;
    }

    private boolean isSelfDividingByString(int number) {
        String digits = String.valueOf(number);

        for (int index = 0; index < digits.length(); index++) {
            int digit = digits.charAt(index) - '0';

            if (digit == 0 || number % digit != 0) {
                return false;
            }
        }

        return true;
    }

    private boolean isSelfDividing(int number) {
        int current = number;

        while (current > 0) {
            int digit = current % 10;

            if (digit == 0 || number % digit != 0) {
                return false;
            }

            current /= 10;
        }

        return true;
    }

    private static void check(String name, List<Integer> actual, List<Integer> expected) {
        if (!actual.equals(expected)) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SelfDividingNumbers solution = new SelfDividingNumbers();

        check("brute force finds self dividing numbers in a sample range",
                solution.bruteForceSelfDividingNumbers(1, 22),
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22));
        check("brute force rejects values with zero digits",
                solution.bruteForceSelfDividingNumbers(100, 105),
                Arrays.asList());

        check("finds self dividing numbers in a sample range",
                solution.selfDividingNumbers(1, 22),
                Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22));
        check("handles a single valid number", solution.selfDividingNumbers(128, 128), Arrays.asList(128));
        check("rejects values with zero digits", solution.selfDividingNumbers(100, 105), Arrays.asList());
    }
}

/*
 * Brute Force:
 * I convert each number to a string, inspect every character digit, and keep the
 * number only when none of its digits are zero and every digit divides it.
 *
 * Time Complexity: O(n * d), where n is the size of the range and d is the
 * maximum number of digits in a number.
 * Space Complexity: O(n + d), because the answer can hold every number and each
 * string conversion stores up to d digits.
 *
 * Optimal Interview Solution:
 * I inspect each digit with modulo and division, avoiding the intermediate
 * string allocation while applying the same divisibility rule.
 *
 * Time Complexity: O(n * d), where n is the size of the range and d is the
 * maximum number of digits in a number.
 * Space Complexity: O(n), because only the answer list grows with the range.
 */
