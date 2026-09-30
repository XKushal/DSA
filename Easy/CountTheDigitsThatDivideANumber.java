class CountTheDigitsThatDivideANumber {
    public int bruteForceCountDigits(int num) {
        int count = 0;
        String digits = String.valueOf(num);

        for (int i = 0; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';

            if (digit != 0 && num % digit == 0) {
                count++;
            }
        }

        return count;
    }

    public int countDigits(int num) {
        int original = num;
        int count = 0;

        while (num > 0) {
            int digit = num % 10;

            if (digit != 0 && original % digit == 0) {
                count++;
            }

            num /= 10;
        }

        return count;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountTheDigitsThatDivideANumber solution = new CountTheDigitsThatDivideANumber();

        check("brute force sample one", solution.bruteForceCountDigits(7), 1);
        check("brute force sample two", solution.bruteForceCountDigits(121), 2);

        check("sample one", solution.countDigits(7), 1);
        check("sample two", solution.countDigits(121), 2);
        check("sample three", solution.countDigits(1248), 4);
        check("repeated divisor", solution.countDigits(111), 3);
    }
}

/*
 * Brute Force:
 * I convert the number to a string, inspect every digit, and count each digit
 * that evenly divides the original number.
 *
 * Time Complexity: O(d), where d is the number of digits.
 * Space Complexity: O(d), because the string representation stores every digit.
 *
 * Optimal Interview Solution:
 * I peel digits from the number with modulo arithmetic and compare each digit
 * with the unchanged original number.
 *
 * Time Complexity: O(d), because each digit is inspected once.
 * Space Complexity: O(1), because only counters and the current digit are
 * stored.
 */
