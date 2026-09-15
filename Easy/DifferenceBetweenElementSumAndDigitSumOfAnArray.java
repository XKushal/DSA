class DifferenceBetweenElementSumAndDigitSumOfAnArray {
    public int bruteForceDifferenceOfSum(int[] nums) {
        int elementSum = 0;
        int digitSum = 0;

        for (int num : nums) {
            elementSum += num;

            String value = String.valueOf(num);
            for (int i = 0; i < value.length(); i++) {
                digitSum += value.charAt(i) - '0';
            }
        }

        return Math.abs(elementSum - digitSum);
    }

    public int differenceOfSum(int[] nums) {
        int elementSum = 0;
        int digitSum = 0;

        for (int num : nums) {
            elementSum += num;
            digitSum += digitSum(num);
        }

        return Math.abs(elementSum - digitSum);
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
        DifferenceBetweenElementSumAndDigitSumOfAnArray solution =
                new DifferenceBetweenElementSumAndDigitSumOfAnArray();

        check("brute force sample one", solution.bruteForceDifferenceOfSum(new int[] {1, 15, 6, 3}), 9);
        check("brute force sample two", solution.bruteForceDifferenceOfSum(new int[] {1, 2, 3, 4}), 0);

        check("sample one", solution.differenceOfSum(new int[] {1, 15, 6, 3}), 9);
        check("sample two", solution.differenceOfSum(new int[] {1, 2, 3, 4}), 0);
        check("mixed widths", solution.differenceOfSum(new int[] {10, 22, 101, 7}), 126);
        check("single element", solution.differenceOfSum(new int[] {99}), 81);
    }
}

/*
 * Brute Force:
 * I add every number to the element sum, convert each number to a string, and
 * add the numeric value of each character to the digit sum.
 *
 * Time Complexity: O(n * d), where n is the length of nums and d is the
 * maximum number of digits in a value.
 * Space Complexity: O(d), because each string representation stores its digits.
 *
 * Optimal Interview Solution:
 * I add every number to the element sum, then compute each digit sum with
 * repeated modulo and division instead of building strings.
 *
 * Time Complexity: O(n * d), where n is the length of nums and d is the
 * maximum number of digits in a value.
 * Space Complexity: O(1), because only scalar totals are stored.
 */
