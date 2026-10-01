class CountIntegersWithEvenDigitSum {
    public int bruteForceCountEven(int num) {
        int count = 0;

        for (int value = 1; value <= num; value++) {
            if (digitSum(value) % 2 == 0) {
                count++;
            }
        }

        return count;
    }

    public int countEven(int num) {
        return (num - (digitSum(num) % 2)) / 2;
    }

    private int digitSum(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountIntegersWithEvenDigitSum solution = new CountIntegersWithEvenDigitSum();

        check("brute force sample one", solution.bruteForceCountEven(4), 2);
        check("brute force sample two", solution.bruteForceCountEven(30), 14);

        check("sample one", solution.countEven(4), 2);
        check("sample two", solution.countEven(30), 14);
        check("single odd digit", solution.countEven(1), 0);
        check("single even digit", solution.countEven(2), 1);
        check("even digit sum boundary", solution.countEven(1000), 499);
    }
}

/*
 * Brute Force:
 * I test every positive integer from 1 through num, compute each digit sum, and
 * count the values whose digit sum is even.
 *
 * Time Complexity: O(num * d), where d is the number of digits in num.
 * Space Complexity: O(1), because only counters and the current sum are stored.
 *
 * Optimal Interview Solution:
 * The count is balanced in pairs as numbers increase. If num has an even digit
 * sum, num itself completes an even-count pair; otherwise, the count matches
 * the previous value before halving.
 *
 * Time Complexity: O(d), where d is the number of digits in num.
 * Space Complexity: O(1), because only the digit sum is stored.
 */
