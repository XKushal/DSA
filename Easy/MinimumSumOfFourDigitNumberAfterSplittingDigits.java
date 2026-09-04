import java.util.Arrays;

class MinimumSumOfFourDigitNumberAfterSplittingDigits {
    public int bruteForceMinimumSum(int num) {
        int[] digits = getDigits(num);
        int best = Integer.MAX_VALUE;

        for (int firstTens = 0; firstTens < digits.length; firstTens++) {
            for (int firstOnes = 0; firstOnes < digits.length; firstOnes++) {
                if (firstOnes == firstTens) {
                    continue;
                }

                for (int secondTens = 0; secondTens < digits.length; secondTens++) {
                    if (secondTens == firstTens || secondTens == firstOnes) {
                        continue;
                    }

                    for (int secondOnes = 0; secondOnes < digits.length; secondOnes++) {
                        if (secondOnes == firstTens || secondOnes == firstOnes || secondOnes == secondTens) {
                            continue;
                        }

                        int firstNumber = digits[firstTens] * 10 + digits[firstOnes];
                        int secondNumber = digits[secondTens] * 10 + digits[secondOnes];
                        best = Math.min(best, firstNumber + secondNumber);
                    }
                }
            }
        }

        return best;
    }

    public int minimumSum(int num) {
        int[] digits = getDigits(num);
        Arrays.sort(digits);

        return (digits[0] * 10 + digits[2]) + (digits[1] * 10 + digits[3]);
    }

    private int[] getDigits(int num) {
        int[] digits = new int[4];

        for (int i = 3; i >= 0; i--) {
            digits[i] = num % 10;
            num /= 10;
        }

        return digits;
    }

    private static void check(String name, int actual, int expected) {
        if (actual != expected) {
            throw new AssertionError(name + " expected " + expected + " but got " + actual);
        }
    }

    public static void main(String[] args) {
        MinimumSumOfFourDigitNumberAfterSplittingDigits solution =
            new MinimumSumOfFourDigitNumberAfterSplittingDigits();

        check("brute force sample", solution.bruteForceMinimumSum(2932), 52);
        check("brute force ordered", solution.bruteForceMinimumSum(4009), 13);

        check("sample", solution.minimumSum(2932), 52);
        check("with zeros", solution.minimumSum(4009), 13);
        check("same digits", solution.minimumSum(1111), 22);
        check("descending digits", solution.minimumSum(9876), 147);
    }
}

/*
 * Brute Force:
 * I extract the four digits, try every ordered split into two two-digit
 * numbers, and keep the smallest sum.
 *
 * Time Complexity: O(1), because the input always has exactly four digits and
 * the exhaustive loops have a fixed size.
 * Space Complexity: O(1), because the digit array always stores four values.
 *
 * Optimal Interview Solution:
 * I sort the digits, put the two smallest digits in the tens places, and put
 * the two largest digits in the ones places.
 *
 * Time Complexity: O(1), because sorting four digits is constant work.
 * Space Complexity: O(1), because the digit array has a fixed size of four.
 */
