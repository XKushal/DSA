class DivisibleAndNonDivisibleSumsDifference {
    public int bruteForceDifferenceOfSums(int n, int m) {
        int nonDivisibleSum = 0;
        int divisibleSum = 0;

        for (int number = 1; number <= n; number++) {
            if (number % m == 0) {
                divisibleSum += number;
            } else {
                nonDivisibleSum += number;
            }
        }

        return nonDivisibleSum - divisibleSum;
    }

    public int differenceOfSums(int n, int m) {
        int totalSum = n * (n + 1) / 2;
        int divisibleCount = n / m;
        int divisibleSum = m * divisibleCount * (divisibleCount + 1) / 2;
        int nonDivisibleSum = totalSum - divisibleSum;

        return nonDivisibleSum - divisibleSum;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        DivisibleAndNonDivisibleSumsDifference solution =
                new DivisibleAndNonDivisibleSumsDifference();

        check("brute force sample one", solution.bruteForceDifferenceOfSums(10, 3), 19);
        check("brute force sample two", solution.bruteForceDifferenceOfSums(5, 6), 15);

        check("sample one", solution.differenceOfSums(10, 3), 19);
        check("sample two", solution.differenceOfSums(5, 6), 15);
        check("sample three", solution.differenceOfSums(5, 1), -15);
        check("no divisible values", solution.differenceOfSums(3, 5), 6);
        check("mixed values", solution.differenceOfSums(12, 4), 30);
    }
}

/*
 * Brute Force:
 * I scan every number from 1 through n, add multiples of m to one sum, add the
 * rest to another sum, and return their difference.
 *
 * Time Complexity: O(n), where n is the upper bound.
 * Space Complexity: O(1), because only two running sums are stored.
 *
 * Optimal Interview Solution:
 * I compute the total sum from 1 through n, then use the arithmetic-series
 * formula for the sum of multiples of m. The non-divisible sum is the remaining
 * part of the total.
 *
 * Time Complexity: O(1), because the answer comes from fixed arithmetic.
 * Space Complexity: O(1), because no extra storage grows with the input.
 */
