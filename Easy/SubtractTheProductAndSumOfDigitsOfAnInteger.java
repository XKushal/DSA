import java.util.ArrayList;
import java.util.List;

class SubtractTheProductAndSumOfDigitsOfAnInteger {
    public int bruteForceSubtractProductAndSum(int n) {
        List<Integer> digits = new ArrayList<>();
        int value = n;

        while (value > 0) {
            digits.add(value % 10);
            value /= 10;
        }

        int product = 1;
        int sum = 0;

        for (int digit : digits) {
            product *= digit;
            sum += digit;
        }

        return product - sum;
    }

    public int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;

        while (n > 0) {
            int digit = n % 10;
            product *= digit;
            sum += digit;
            n /= 10;
        }

        return product - sum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        SubtractTheProductAndSumOfDigitsOfAnInteger solution =
            new SubtractTheProductAndSumOfDigitsOfAnInteger();

        check("brute force sample", solution.bruteForceSubtractProductAndSum(234), 15);
        check("brute force includes one", solution.bruteForceSubtractProductAndSum(4421), 21);

        check("sample", solution.subtractProductAndSum(234), 15);
        check("includes one", solution.subtractProductAndSum(4421), 21);
        check("contains zero", solution.subtractProductAndSum(105), -6);
    }
}

/*
 * Brute Force:
 * I extract all digits into a list first, then make a second pass to calculate
 * the digit product and sum.
 *
 * Time Complexity: O(d), because each digit is processed a constant number of
 * times.
 * Space Complexity: O(d), because all digits are stored before the arithmetic
 * pass.
 *
 * Optimal Interview Solution:
 * I calculate the product and sum while extracting each digit, so the number is
 * processed in one pass without storing the digits.
 *
 * Time Complexity: O(d), because each digit is extracted once.
 * Space Complexity: O(1), because only the running product and sum are stored.
 */
