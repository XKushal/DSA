class CountSymmetricIntegers {
    public int bruteForceCountSymmetricIntegers(int low, int high) {
        int count = 0;

        for (int number = low; number <= high; number++) {
            String value = String.valueOf(number);

            if (value.length() % 2 == 1) {
                continue;
            }

            int leftSum = 0;
            int rightSum = 0;
            int middle = value.length() / 2;

            for (int i = 0; i < middle; i++) {
                leftSum += value.charAt(i) - '0';
                rightSum += value.charAt(i + middle) - '0';
            }

            if (leftSum == rightSum) {
                count++;
            }
        }

        return count;
    }

    public int countSymmetricIntegers(int low, int high) {
        int count = 0;

        for (int number = low; number <= high; number++) {
            if (isSymmetric(number)) {
                count++;
            }
        }

        return count;
    }

    private boolean isSymmetric(int number) {
        if (number >= 10 && number <= 99) {
            return number / 10 == number % 10;
        }

        if (number >= 1000 && number <= 9999) {
            int firstHalf = number / 100;
            int secondHalf = number % 100;
            return digitSum(firstHalf) == digitSum(secondHalf);
        }

        return false;
    }

    private int digitSum(int number) {
        int sum = 0;

        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }

        return sum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        CountSymmetricIntegers solution = new CountSymmetricIntegers();

        check("brute force two digits", solution.bruteForceCountSymmetricIntegers(1, 100), 9);
        check("brute force four digits", solution.bruteForceCountSymmetricIntegers(1200, 1230), 4);

        check("sample one", solution.countSymmetricIntegers(1, 100), 9);
        check("sample two", solution.countSymmetricIntegers(1200, 1230), 4);
        check("single symmetric value", solution.countSymmetricIntegers(99, 99), 1);
        check("skip odd digit counts", solution.countSymmetricIntegers(100, 1000), 0);
        check("four digit range", solution.countSymmetricIntegers(1000, 1010), 2);
    }
}

/*
 * Brute Force:
 * I convert every number in the range to a string, split its digits in half,
 * and compare the digit sums for even-length values.
 *
 * Time Complexity: O((high - low + 1) * d), where d is the number of digits.
 * Space Complexity: O(d), because each checked number is converted to a string.
 *
 * Optimal Interview Solution:
 * I use the problem's bounds directly: only two-digit and four-digit numbers
 * can be symmetric, so each number can be checked with arithmetic digit sums.
 *
 * Time Complexity: O(high - low + 1), because each number is checked in
 * constant time.
 * Space Complexity: O(1), because no extra storage grows with the input range.
 */
